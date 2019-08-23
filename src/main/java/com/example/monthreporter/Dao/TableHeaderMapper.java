package com.example.monthreporter.Dao;

import com.example.monthreporter.Entity.Data;
import com.example.monthreporter.Entity.TableHeader;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: lurenqiang
 * @Date: 2019/8/22
 * @Description:
 */
@Component
@Mapper
public interface TableHeaderMapper {
    List<TableHeader> SelectTableHeader();

    List<Data> SelectData();
}
