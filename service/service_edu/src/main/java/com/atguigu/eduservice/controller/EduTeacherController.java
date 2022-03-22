package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;
import com.atguigu.eduservice.vo.TeacherQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-02-20
 */
@Api("讲师管理")
@RestController
//@CrossOrigin
public class EduTeacherController {

    @Resource
    private EduTeacherService eduTeacherService;

    @ApiOperation("所有讲师列表")
    @GetMapping("/eduservice/eduteacher/findAll")
    public R findAll() {
        List<EduTeacher> teacherList = eduTeacherService.list(null);
        return R.success().data("items",teacherList);
    }

    @ApiOperation("根据id删除讲师")
    @DeleteMapping("/eduservice/eduteacher/delete/{id}")
    public R deleteById(@ApiParam(name = "id", value = "讲师id", required = true)
                              @PathVariable("id") String id) {
        Boolean flag = eduTeacherService.removeById(id);
        return flag ? R.success() : R.error();
    }

    @ApiOperation("分页查询讲师")
    @GetMapping("/eduservice/eduteacher/pageTeacher/{current}/{size}")
    public R pageListTeacher(@ApiParam(name = "current", value = "当前页码", required = true)
                             @PathVariable("current") long current,
                             @ApiParam(name = "size", value = "每页记录数", required = true)
                             @PathVariable("size") long size) {

        Page<EduTeacher> page = new Page<>(current, size);
        IPage<EduTeacher> eduTeacherPage = eduTeacherService.page(page, null);

        return R.success()
                .data("total", eduTeacherPage.getTotal())
                .data("rows", eduTeacherPage.getRecords());
    }

    @ApiOperation("多条件分页查询讲师")
    @PostMapping("/eduservice/eduteacher/pageTeacherCondition/{current}/{size}")
    public R pageTeacherCondition(@ApiParam(name = "current", value = "当前页码", required = true)
                                  @PathVariable("current") long current,
                                  @ApiParam(name = "size", value = "每页记录数", required = true)
                                  @PathVariable("size") long size,
                                  @RequestBody(required = false) TeacherQuery teacherQuery) {

        Page<EduTeacher> page = new Page<>(current, size);
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();

        System.out.println(teacherQuery);
        if (teacherQuery == null) {
            teacherQuery = new TeacherQuery();
        }
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            queryWrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }

        queryWrapper.orderByDesc("gmt_create");

        IPage<EduTeacher> eduTeacherPage = eduTeacherService.page(page, queryWrapper);
        return R.success()
                .data("total", eduTeacherPage.getTotal())
                .data("rows", eduTeacherPage.getRecords());
    }

    @ApiOperation("添加讲师")
    @PostMapping("/eduservice/eduteacher/addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {

        boolean result = eduTeacherService.save(eduTeacher);
        return result ? R.success() : R.error();
    }

    @ApiOperation("根据id查询讲师")
    @GetMapping("/eduservice/eduteacher/get/{id}")
    public R getTeacherById(@PathVariable("id") String id) {

        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return R.success().data("teacher", eduTeacher);
    }

    @ApiOperation("更新讲师")
    @PostMapping("/eduservice/eduteacher/updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {

        boolean result = eduTeacherService.updateById(eduTeacher);
        return result ? R.success() : R.error();
    }
}

