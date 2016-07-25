package com.wow.adminapi.service.impl;

import com.wow.adminapi.mapper.EmployeeMapper;
import com.wow.adminapi.model.Employee;
import com.wow.adminapi.model.EmployeeExample;
import com.wow.adminapi.service.EmployeeService;
import com.wow.adminapi.service.EmployeeSessionService;
import com.wow.adminapi.vo.*;
import com.wow.common.util.BeanUtil;
import com.wow.common.util.ErrorCodeUtil;
import com.wow.user.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by zhengzhiqing on 16/7/14.
 */
@Service
@Transactional("adminTransactionManager")
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeSessionService employeeSessionService;

    /**
     * 用户注册
     *
     * @param registerRequest
     * @return
     */
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        RegisterResponse registerResponse = new RegisterResponse();
        String userName = registerRequest.getUserName();
        //用户名,密码,真实姓名,手机,邮件地址不能为空
        if (StringUtils.isEmpty(userName)) {
            registerResponse.setResCode("40000");
            registerResponse.setResMsg("用户名不能为空");
            return registerResponse;
        }

        //判断该手机号是否已经注册
        if (isExistedEmployeeByUserName(userName).isExistedEmployee()) {
            registerResponse.setResCode("40104");
            registerResponse.setResMsg(ErrorCodeUtil.getErrorMsg("40104"));
            return registerResponse;
        }

        registerRequest.setPassword(
                PasswordUtil.passwordHashGenerate(registerRequest.getPassword()));
        //bean copy from registerRequest to endUser
        Employee employee = new Employee();
        BeanUtil.copyProperties(registerRequest,employee);

        employeeMapper.insertSelective(employee);
        //注册成功,需要将用户ID返回
        registerResponse.setEmployeeId(employee.getId());
        return registerResponse;
    }

    /**
     * 根据用户名判断是否已注册用户
     *
     * @param userName
     * @return
     */
    @Override
    public EmployeeCheckResponse isExistedEmployeeByUserName(String userName) {
        EmployeeCheckResponse employeeCheckResponse = new EmployeeCheckResponse();
        Employee employee = getEmployeeByUserName(userName).getEmployee();
        employeeCheckResponse.setExistedEmployee(employee != null);
        return employeeCheckResponse;
    }

    /**
     * 根据手机号判断是否已注册用户
     *
     * @param mobile
     * @return
     */
    @Override
    public EmployeeCheckResponse isExistedEmployeeByMobile(String mobile) {
        EmployeeCheckResponse employeeCheckResponse = new EmployeeCheckResponse();
        Employee employee = getEmployeeByMobile(mobile).getEmployee();
        employeeCheckResponse.setExistedEmployee(employee != null);
        return employeeCheckResponse;
    }

    /**
     * 用户信息更新
     *
     * @param employee
     * @return
     */
    @Override
    public EmployeeUpdateResponse updateEmployee(Employee employee) {
        EmployeeUpdateResponse employeeUpdateResponse = new EmployeeUpdateResponse();
        if (employee != null && employee.getId() != null) {
            int i= employeeMapper.updateByPrimaryKeySelective(employee);
            employeeUpdateResponse.setSuccess(i>0);
        } else {
            employeeUpdateResponse.setResCode("40106");
            employeeUpdateResponse.setResMsg(ErrorCodeUtil.getErrorMsg("40106"));
        }
        return employeeUpdateResponse;
    }

    /**

    /**
     * 用户忘记密码/重置密码 - 需要手机验证(以后可支持邮件验证?)
     *
     * @param mobile
     * @param captcha
     * @param newPwd
     * @return
     */
    @Override
    public ResetPwdResponse resetPassword(String mobile, String captcha, String newPwd) {
        ResetPwdResponse resetPwdResponse = new ResetPwdResponse();
        String captchaForMobile = getCaptchaOnServer(mobile);
        if (captchaForMobile.equals(captcha)) {
            EmployeeResponse employeeResponse = getEmployeeByMobile(mobile);
            Employee employee = employeeResponse.getEmployee();
            if(employee== null) {
                logger.error("该用户不存在");
                resetPwdResponse.setResCode("50599");
                resetPwdResponse.setResCode(ErrorCodeUtil.getErrorMsg("50599"));
            }
            employee.setPassword(PasswordUtil.passwordHashGenerate(newPwd));
            employee.setUpdateTime(new Date());
            if (employeeMapper.updateByPrimaryKeySelective(employee)>0) {
                //需要设置当前有效的session token失效
                employeeSessionService.invalidateSessionToken(employee.getId());
            } else {
                resetPwdResponse.setResCode("50519");
                resetPwdResponse.setResCode(ErrorCodeUtil.getErrorMsg("50519"));
            }
        } else {
            logger.info("验证码无效,请重新获取");
            resetPwdResponse.setResCode("50598");
            resetPwdResponse.setResCode(ErrorCodeUtil.getErrorMsg("50598"));
        }
        return resetPwdResponse;
    }

    /**
     * 获取服务器上生成的验证码
     * @param mobile
     * @return
     */
    @Transactional(propagation= Propagation.SUPPORTS)
    private String getCaptchaOnServer(String mobile) {
        String captchaOnServer = "111111"; //TODO, hard code for test only
//        //get from redis
//        Object captcha = RedisUtil.get(mobile);
//        if (captcha != null) {
//            captchaOnServer = (String) captcha;
//        }
        return captchaOnServer;
    }
    /**
     * 根据Id获取用户信息
     *
     * @param employeeId
     * @return
     */
    @Transactional(propagation= Propagation.SUPPORTS)
//    @Cacheable(value = "UserCache",key="'USER_ID_'+#endUserId")
    public EmployeeResponse getEmployeeById(int employeeId) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        Employee employee = employeeMapper.selectByPrimaryKey(employeeId);
        if (employee != null) {
            employeeResponse.setEmployee(employee);
        } else {
            employeeResponse.setResCode("50505");
            employeeResponse.setResMsg("该用户不存在");
        }
        return employeeResponse;
    }

    /**
     * 根据用户名获取用户信息
     *
     * @param userName
     * @return
     */
    @Override
    @Transactional(propagation= Propagation.SUPPORTS)
    @Cacheable(value = "UserCache",key="'USER_NAME_'+#userName")
    public EmployeeResponse getEmployeeByUserName(String userName) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andUserNameEqualTo(userName);
        criteria.andIsDeletedEqualTo(false);
        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
        Employee employee;
        if (employeeList.size() > 1) {
            logger.error("找到多条该用户名对应的用户");
            employeeResponse.setResCode("50505");
            employeeResponse.setResMsg(ErrorCodeUtil.getErrorMsg("50505"));
        } else if (employeeList.size() == 1) {
            employee =  employeeList.get(0);
            employeeResponse.setEmployee(employee);
        } else {
            logger.error("找不到该用户名对应的用户");
            employeeResponse.setResCode("50506");
            employeeResponse.setResMsg(ErrorCodeUtil.getErrorMsg("50506"));
        }
        return employeeResponse;
    }

    /**
     * 根据手机号获取用户信息
     *
     * @param mobile
     * @return
     */
    @Override
    @Transactional(propagation= Propagation.SUPPORTS)
//    @Cacheable(value = "UserCache", key="'USER_MOBILE_'+#mobile")
    public EmployeeResponse getEmployeeByMobile(String mobile) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andMobileEqualTo(mobile);
        criteria.andIsDeletedEqualTo(false);
        Employee employee;
        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
        if (employeeList.size() > 1) {
            logger.error("找到多条该手机对应的用户");
            employeeResponse.setResCode("50505");
            employeeResponse.setResMsg(ErrorCodeUtil.getErrorMsg("50505"));
        } else if (employeeList.size() == 1) {
            employee =  employeeList.get(0);
            employeeResponse.setEmployee(employee);
        } else {
            logger.error("找不到该手机对应的用户");
            employeeResponse.setResCode("50506");
            employeeResponse.setResMsg(ErrorCodeUtil.getErrorMsg("50506"));
        }

        return employeeResponse;
    }

    /**
     * 批量查询多个用户
     * 一般是运营后台调用
     *
     * @param employeeIds
     * @return
     */
    @Override
    @Transactional(propagation= Propagation.SUPPORTS)
    @Cacheable(value = "UserCache")
    public List<Employee> getEmployeesByIds(int[] employeeIds) {
        //TODO:
        return null;
    }

    /**
     * 验证手机号(也是用户名)、密码是否匹配
     *
     * @param userName
     * @param password
     * @return
     */
    @Transactional(propagation= Propagation.SUPPORTS)
    public EmployeeResponse authenticate(String userName, String password) {
        logger.info("start to auth:" + userName + "," + password);
        EmployeeResponse authResponse = new EmployeeResponse();
        EmployeeResponse employeeResponse = getEmployeeByUserName(userName);
        if (employeeResponse == null || employeeResponse.getEmployee() == null) {
            authResponse.setResCode("50503");
            authResponse.setResCode(ErrorCodeUtil.getErrorMsg("50503"));
        } else {
            Employee employee = employeeResponse.getEmployee();
            if (employee!=null
                    && employee.getPassword()!=null
                    && PasswordUtil.passwordHashValidate(password, employee.getPassword())) {
                logger.info("matched");
                authResponse.setEmployee(employee);
            } else {
                authResponse.setResCode("50503");
                authResponse.setResCode(ErrorCodeUtil.getErrorMsg("50503"));
            }
        }
        return authResponse;
    }

//    /**
//     * 请求并发送验证码
//     * 注册,修改密码或者多次输入错误的情况下使用
//     *
//     * @param mobile
//     * @return 验证码
//     */
//    public CaptchaResponse sendCaptcha(String mobile) {
//
//        CaptchaResponse captchaResponse = new CaptchaResponse();
//
//        //调用阿里大鱼的短信接口,往目标手机发送随机生成的6位数字,并将6位数字存储到Redis中
//        //1. generate 6-bit digit randomly
//        String randomNum = RandomGenerator.createRandom(true,6);
//
//        //2. call alidayu interface to send sms
//        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
//        req.setExtend( "13764641531 uid " );//公共回传参数，在“消息返回”中会透传回该参数；举例：用户可以传入自己下级的会员ID，在消息返回时，该会员ID会包含在内，用户可以根据该会员ID识别是哪位会员使用了你的应用
//        req.setSmsType("normal");//短信类型，传入值请填写normal
//        req.setSmsFreeSignName("尖叫设计");//短信签名，传入的短信签名必须是在阿里大鱼“管理中心-短信签名管理”中的可用签名。如“阿里大鱼”已在短信签名管理中通过审核，则可传入”阿里大鱼“（传参时去掉引号）作为短信签名。短信效果示例：【阿里大鱼】欢迎使用阿里大鱼服务。
//        req.setSmsParamString("{'code':'" + randomNum + "','product':'尖叫设计'}");//短信模板变量，传参规则{"key":"value"}，key的名字须和申请模板中的变量名一致，多个变量之间以逗号隔开。示例：针对模板“验证码${code}，您正在进行${product}身份验证，打死不要告诉别人哦！”，传参时需传入{"code":"1234","product":"alidayu"}
//        req.setRecNum(mobile);//短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。示例：18600000000,13911111111,13322222222
//        req.setSmsTemplateCode("SMS_5165048");//短信模板ID，传入的模板必须是在阿里大鱼“管理中心-短信模板管理”中的可用模板。示例：SMS_585014
//        logger.info("request=" + req);
//        smsSender.sendValidateCode(req);
//
//        //3. store digit into redis
//        //TODO: 是否所有验证码的过期时间一样,还是需要配置?
//        RedisUtil.set(mobile,randomNum,captchaTimeout);//缓存无限长,需要做成配置
//
//        captchaResponse.setCaptcha(randomNum);
//
//        return captchaResponse;
//    }

}
