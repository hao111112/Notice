package com.hystrix.notice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hystrix.notice.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
}
