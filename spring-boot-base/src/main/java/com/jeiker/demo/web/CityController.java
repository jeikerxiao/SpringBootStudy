package com.jeiker.demo.web;
import com.jeiker.demo.core.Result;
import com.jeiker.demo.core.ResultGenerator;
import com.jeiker.demo.model.City;
import com.jeiker.demo.model.vo.IdVo;
import com.jeiker.demo.model.vo.PageInfoVo;
import com.jeiker.demo.service.CityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author : xiao
 * @Date : 2017/07/31
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/add")
    public Result add(@RequestBody City city) {
        cityService.save(city);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody IdVo idVo) {
        cityService.deleteById(idVo.getId());
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody City city) {
        cityService.update(city);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestBody IdVo idVo) {
        City city = cityService.findById(idVo.getId());
        return ResultGenerator.genSuccessResult(city);
    }

    @PostMapping("/list")
    public Result list(@RequestBody PageInfoVo pageInfoVo) {
        PageHelper.startPage(pageInfoVo.getPage(), pageInfoVo.getSize());
        List<City> list = cityService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
