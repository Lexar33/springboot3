package org.jalcantararivera.mitosales.service.impl;

import lombok.RequiredArgsConstructor;
import org.jalcantararivera.mitosales.model.Category;
import org.jalcantararivera.mitosales.repo.ICategoryRepo;
import org.jalcantararivera.mitosales.repo.IGenericRepo;
import org.jalcantararivera.mitosales.service.ICategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl extends CRUDImpl<Category,Integer> implements ICategoryService {

    private final ICategoryRepo repo;

    @Override
    protected IGenericRepo<Category, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Category> findByNameService(String name) {
        return repo.findByNameLike("%"+name+"%");
    }

    @Override
    public List<Category> findByNameAndEnabledService(String name, boolean enabled) {
        return repo.findByNameAndEnabled(name,enabled);
    }

    @Override
    public List<Category> getByNameAndDescriptionService(String name, String description) {
        return repo.getNameAndDescription2(name,description);
    }

    @Override
    public Page<Category> findPage(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public List<Category> findAllOrder(String param){
        Sort.Direction direction = param.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        return repo.findAll(Sort.by(direction,"name"));
        ///repo.save()
    }

    //@Autowired
    //private CategoryRepo repo;
    //INYECCION POR CONSTRUCTOR
    /*
    public CategoryService(CategoryRepo repo){
        this.repo = repo;
    }
    */

    /*
    public Category validateCategory(Category category){
        //repo = new CategoryRepo();

        if(category.getIdCategory() != 0){
            return repo.getCategoryX(category);
        } else {
            return new Category();

        }
    }


    @Override
    public Category save(Category category) throws Exception {
        return repo.save(category);
    }

    @Override
    public Category update(Category category, Integer id) throws Exception {
        category.setIdCategory(id);
        return repo.save(category);
    }

    @Override
    public List<Category> readAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Category readById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Category());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
*/
}
