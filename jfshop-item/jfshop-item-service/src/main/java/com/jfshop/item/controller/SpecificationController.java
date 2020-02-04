package com.jfshop.item.controller;

import com.jfshop.item.pojo.SpecGroup;
import com.jfshop.item.pojo.SpecParam;
import com.jfshop.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("spec")
public class SpecificationController {
    @Autowired
    private SpecificationService specificationService;


    /**
     * 根据分类id查询参数组
     * @param cid
     * @return
     */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupsByCid(@PathVariable("cid") Long cid){
        List<SpecGroup> groups =  specificationService.queryGroupsByCid(cid);
        if(CollectionUtils.isEmpty(groups)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(groups);
    }

    /**
     * 根据gid查询规格参数
     * @param gid
     * @return
     */
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> queryParams(
         @RequestParam(value = "gid",required = false) Long gid,
         @RequestParam(value = "cid",required = false) Long cid,
         @RequestParam(value = "generic",required = false) Boolean generic,
         @RequestParam(value = "searching",required = false) Boolean searching
        ){
        List<SpecParam> specParams =  specificationService.queryParams(gid,cid,generic,searching);
        if(CollectionUtils.isEmpty(specParams)){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(specParams);
    }
}
