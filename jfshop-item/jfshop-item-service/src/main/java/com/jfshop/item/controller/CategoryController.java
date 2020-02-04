package com.jfshop.item.controller;

import com.jfshop.item.pojo.Category;
import com.jfshop.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 根据父节点id查询子节点
     * @param pid
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoriesByPid(@RequestParam(value="pid",defaultValue = "0")Long pid){
        try {
            if(pid == null || pid < 0 ){
                //400参数不合法
               /* return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();*/
               /* return new ResponseEntity<>(HttpStatus.BAD_REQUEST); *///另一种写法
                return ResponseEntity.badRequest().build();
            }

            List<Category> categories = this.categoryService.queryCategoriesByPid(pid);

            if(CollectionUtils.isEmpty(categories)){
                //404资源服务器未找到
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            //200查询成功
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //500：服务器内部错误
        return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE).build();
    }


    /**
     * 用于修改品牌信息时，商品分类信息的回显
     * @param bid
     * @return
     */
    @GetMapping("bid/{bid}")
    public ResponseEntity<List<Category>> queryByBrandId(@PathVariable("bid") Long bid){
        List<Category> list = this.categoryService.queryByBrandId(bid);
        if(list == null || list.size() < 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(list);
    }


    /**
     * 保存
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveCategory(Category category){
        this.categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 更新
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateCategory(Category category){
        this.categoryService.updateCategory(category);
        return  ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * 删除
     * @return
     */
    @DeleteMapping("cid/{cid}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("cid") Long id){
        this.categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 根据分类id集合查询分类名称
     * @param ids
     * @return
     */
    @GetMapping("names")
    public ResponseEntity<List<String>> queryNameByIds(@RequestParam("ids")List<Long> ids){
        List<String> list = categoryService.queryNameByIds(ids);
        if (list == null || list.size() < 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.ok(list);
        }
    }

    /**
     * 根据分类id集合查询分类名称
     * @param ids
     * @return
     */
/*    @GetMapping("all")
    public ResponseEntity<List<Category>> queryCategoryByIds(@RequestParam("ids")List<Long> ids){
        List<Category> list = categoryService.queryCategoryByIds(ids);
        if (list == null || list.size() < 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.ok(list);
        }
    }*/

    /**
     * 根据分类id集合查询分类名称
     * @param id
     * @return
     */
    @GetMapping("all/level/{cid3}")
    public ResponseEntity<List<Category>> queryAllCategoryLevelByCid3(@PathVariable("cid3")Long id){
        List<Category> list = categoryService.queryAllCategoryLevelByCid3(id);
        if (list == null || list.size() < 1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.ok(list);
        }
    }
}
