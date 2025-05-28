package com.quanweng.shopping.mapper;

import com.quanweng.shopping.pojo.UserTrace;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserTraceMapper {
    void insertUserTrace(UserTrace userTrace);

    List<UserTrace> queryUserTraceByUserId(String userId);

    List<UserTrace> queryUserTrace(
        @Param("userId") String userId,
        @Param("action") String action,
        @Param("ip") String ip,
        @Param("region") String region,
        @Param("startTime") String startTime,
        @Param("endTime") String endTime,
        @Param("offset") int offset,
        @Param("size") int size
    );

    int countUserTrace(
        @Param("userId") String userId,
        @Param("action") String action,
        @Param("ip") String ip,
        @Param("region") String region,
        @Param("startTime") String startTime,
        @Param("endTime") String endTime
    );

    @MapKey("action")
    Map<String, Integer> statisticsByAction(
        @Param("userId") String userId,
        @Param("startTime") String startTime,
        @Param("endTime") String endTime
    );
}
