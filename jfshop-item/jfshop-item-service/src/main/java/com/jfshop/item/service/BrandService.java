package com.jfshop.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jfshop.common.pojo.PageResult;
import com.jfshop.item.mapper.BrandMapper;
import com.jfshop.item.pojo.Brand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private BrandMapper brandMapper;

    /**
     * 根据查询条件分页并排序查询品牌信息
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    public PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        //初始化查询条件对象
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        //根据name模糊查询 或者根据首字母
        if(StringUtils.isNotBlank(key)){
            criteria.andLike("name","%"+key+"%").orEqualTo("letter",key);
        }
        //添加分页条件
        PageHelper.startPage(page,rows);
        //添加排序条件
        if(StringUtils.isNotBlank(sortBy)){
            example.setOrderByClause(sortBy+" "+(desc ? "desc":"asc"));
        }
        List<Brand> brands = this.brandMapper.selectByExample(example);

        PageInfo<Brand> brandPageInfo = new PageInfo<>(brands);

        return new PageResult<>(brandPageInfo.getTotal(),brandPageInfo.getList());
    }

    /**
     * 新增品牌
     * @param brand
     * @param cids
     */
    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        //新增品牌表
        Boolean flag = brandMapper.insertSelective(brand) == 1;
        //获取品牌id 新增品牌分类关联表
        if(flag){
            cids.forEach(cid -> {
                brandMapper.insertCategoryAndBrand(cid,brand.getId());
            });
        }

    }

    public List<Brand> queryBrandsByCid(Long cid) {
        return brandMapper.queryBrandsByCid(cid);
    }
}
