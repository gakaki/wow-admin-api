package com.wow.adminapi.mapper;

import com.wow.adminapi.model.EmployeeLoginLog;
import com.wow.adminapi.model.EmployeeLoginLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeLoginLogMapper {
    int countByExample(EmployeeLoginLogExample example);

    int deleteByExample(EmployeeLoginLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EmployeeLoginLog record);

    int insertSelective(EmployeeLoginLog record);

    List<EmployeeLoginLog> selectByExample(EmployeeLoginLogExample example);

    EmployeeLoginLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EmployeeLoginLog record, @Param("example") EmployeeLoginLogExample example);

    int updateByExample(@Param("record") EmployeeLoginLog record, @Param("example") EmployeeLoginLogExample example);

    int updateByPrimaryKeySelective(EmployeeLoginLog record);

    int updateByPrimaryKey(EmployeeLoginLog record);
}