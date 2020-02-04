package com.jfshop.item.service;

import com.jfshop.item.mapper.SpecGroupMapper;
import com.jfshop.item.mapper.SpecParamMapper;
import com.jfshop.item.pojo.SpecGroup;
import com.jfshop.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class SpecificationService {
        @Autowired
        private SpecGroupMapper groupMapper;
        @Autowired
        private SpecParamMapper paramMapper;

    /**
     * 根据分类id查询参数组
     * @param cid
     * @return
     */
    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup record = new SpecGroup();
        record.setCid(cid);
        return groupMapper.select(record);
    }

    /**
     * 根据gid查询规格参数
     *
     *
     * @param gid
     * @param generic
     * @param searching
     * @return
     */
    public List<SpecParam> queryParams(Long gid, Long cid, Boolean generic, Boolean searching) {
        SpecParam record = new SpecParam();
        record.setGroupId(gid);
        record.setCid(cid);
        record.setGeneric(generic);
        record.setSearching(searching);

        return paramMapper.select(record);


    }


}
