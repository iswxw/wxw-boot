package com.wxw.dao;

import com.wxw.domain.Engines;
import com.wxw.domain.EnginesExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EnginesMapper {

    long countByExample(EnginesExample example);

    int deleteByExample(EnginesExample example);

    int insert(Engines record);

    int insertSelective(Engines record);

    List<Engines> selectByExample(EnginesExample example);

    int updateByExampleSelective(@Param("record") Engines record, @Param("example") EnginesExample example);

    int updateByExample(@Param("record") Engines record, @Param("example") EnginesExample example);
}