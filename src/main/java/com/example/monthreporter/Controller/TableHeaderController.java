package com.example.monthreporter.Controller;

import com.example.monthreporter.Entity.TableHeader;
import com.example.monthreporter.Service.TableHeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: lurenqiang
 * @Date: 2019/8/23
 * @Description:
 */
@RestController
public class TableHeaderController {
    @Autowired
    private TableHeaderService tableHeaderService;

    @RequestMapping("/Table")
    public List<TableHeader> Select()
    {
        return tableHeaderService.SelectTableHeader();
    }

}
