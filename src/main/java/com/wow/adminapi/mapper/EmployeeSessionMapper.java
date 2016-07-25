package com.wow.adminapi.mapper;

import com.wow.adminapi.model.EmployeeSession;
import com.wow.adminapi.model.EmployeeSessionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeSessionMapper {
    int countByExample(EmployeeSessionExample example);

    int deleteByExample(EmployeeSessionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EmployeeSession record);

    int insertSelective(EmployeeSession record);

    List<EmployeeSession> selectByExample(EmployeeSessionExample example);

    EmployeeSession selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EmployeeSession record, @Param("example") EmployeeSessionExample example);

    int updateByExample(@Param("record") EmployeeSession record, @Param("example") EmployeeSessionExample example);

    int updateByPrimaryKeySelective(EmployeeSession record);

    int updateByPrimaryKey(EmployeeSession record);
}