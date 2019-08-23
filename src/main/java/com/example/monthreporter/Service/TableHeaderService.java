package com.example.monthreporter.Service;

import com.example.monthreporter.Entity.Data;
import com.example.monthreporter.Entity.TableHeader;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: lurenqiang
 * @Date: 2019/8/23
 * @Description:
 */
@Service
public interface TableHeaderService {
    List<TableHeader> SelectTableHeader();
    List<Data> SelectData();
}
