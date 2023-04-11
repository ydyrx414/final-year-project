package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.dto.CorporationQueryDTO;
import com.example.springboot.dto.UpdateCorpDTO;
import com.example.springboot.entity.VCorp;
import com.example.springboot.service.ICorporationService;
import com.example.springboot.service.IUserService;
import com.example.springboot.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/corporation")
public class CorporationController {

    @Resource
    private ICorporationService corporationService;

    @Autowired
    private IUserService userService;

    //查询所有数据
    @GetMapping
    public Result findAll() {
        return Result.success(userService.findAllCrops());
    }

    /**
     * 仅更新
     * @param corporation
     * @return
     */
    @PostMapping
    public Result<VCorp> save(@RequestBody UpdateCorpDTO dto) {
        return Result.success(corporationService.update(dto));
    }

    //删除数据
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id) {
        userService.delete(id);
        return Result.success();
    }

    //分页查询，mabatis-plus的方式
    @GetMapping("/page")
    public Result<PageVO<VCorp>> findPage(CorporationQueryDTO dto) {
        return Result.success(corporationService.queryCorporations(dto));
    }
}
