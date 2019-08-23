package com.example.monthreporter.Service.impl;

import com.example.monthreporter.Dao.TableHeaderMapper;
import com.example.monthreporter.Entity.Data;
import com.example.monthreporter.Entity.TableHeader;
import com.example.monthreporter.Service.TableHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: lurenqiang
 * @Date: 2019/8/23
 * @Description:
 */
@Service
public class TableHeaderServiceImpl implements TableHeaderService {
    @Autowired
    private TableHeaderMapper tableHeaderMapper;
    @Override
    public List<TableHeader> SelectTableHeader() {
        return tableHeaderMapper.SelectTableHeader();
    }

    @Override
    public List<Data> SelectData() {
        return tableHeaderMapper.SelectData();
    }
}
