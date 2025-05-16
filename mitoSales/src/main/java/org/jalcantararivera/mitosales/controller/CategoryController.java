package org.jalcantararivera.mitosales.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jalcantararivera.mitosales.dto.CategoryDTO;
import org.jalcantararivera.mitosales.dto.GenericResponse;
import org.jalcantararivera.mitosales.model.Category;
import org.jalcantararivera.mitosales.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    //private final CategoryServiceImpl service;
    private final ICategoryService service;

    @Qualifier("categoryMapper")
    private final ModelMapper modelMapper;

    //INYECCION CON AUTOWIRED
    //@Autowired
    //private ICategoryService service2;

    //INYECCION POR CONSTRUCTOR
    /*
    public CategoryController(CategoryServiceImpl service,ModelMapper,modelMapper){
        this.modelMapper= modelMapper;
        this.service=service;
    }
    */



    /*
    @GetMapping
    public Category getCategorySimple(){
        //service= new CategoryService();
        return service.validateCategory(new Category(1,"TV","Television",true));
    }
    */
    //@PreAuthorize("@authServiceImpl.hasAccess('readAll')")
    //@PreAuthorize("hasAuthority('admin') or hasAuthority('user')")
    @GetMapping
    public ResponseEntity<List<CategoryDTO>>  readAll() throws  Exception{
        //ModelMapper modelMapper= new ModelMapper();
        List<CategoryDTO>list=service.readAll().stream().map(this::convertToDto).toList();
        /*List<CategoryRecord>list2=service.readAll().stream().map(e->new CategoryRecord(
                        e.getIdCategory(),
                        e.getName(),
                        e.getDescription(),
                        e.isEnabled())).
                        toList();
        */
        return ResponseEntity.ok(list);
    }

    /*
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Category obj= service.readById(id);
        return ResponseEntity.ok(convertToDto(obj));
    }
    */
    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<CategoryDTO>> readById(@PathVariable("id") Integer id) throws Exception{
        Category obj= service.readById(id);
        return ResponseEntity.ok(new GenericResponse<>(200,"SUCCESS", Arrays.asList(convertToDto(obj))));
    }
    @PostMapping
    public ResponseEntity<CategoryDTO> save(@Valid @RequestBody CategoryDTO dto) throws  Exception{
        Category obj= service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
        //return new ResponseEntity.created();
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@Valid @RequestBody CategoryDTO dto,@PathVariable("id") Integer id ) throws Exception{
        //dto.setIdCategory(id);
        Category obj= service.update(convertToEntity(dto),id);
        return ResponseEntity.ok(convertToDto(obj));
     }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id ) throws Exception{
        service.delete(id);
        //return ResponseEntity.noContent().build();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    ////////////////////////////////////////////////////
    private CategoryDTO convertToDto(Category obj){
        return modelMapper.map(obj,CategoryDTO.class);
    }
    private Category convertToEntity(CategoryDTO dto){
        return modelMapper.map(dto,Category.class);
    }

    ////////////////////////////////////////////////////
    @GetMapping("find/name/{name}")
    public ResponseEntity<List<CategoryDTO>> findByName(@PathVariable("name")String name){
        List<CategoryDTO> list= service.findByNameService(name).stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("find/name/{name}/{enabled}")
    public ResponseEntity<List<CategoryDTO>> findByNameEnabled(@PathVariable("name")String name,@PathVariable("enabled")Boolean enabled){
        List<CategoryDTO> list= service.findByNameAndEnabledService(name,enabled).stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/get/name/description")
    public ResponseEntity<List<CategoryDTO>> findByNameDescription(@RequestParam("name")String name,@RequestParam("description")String description){
        List<CategoryDTO> list= service.getByNameAndDescriptionService(name,description).stream().map(this::convertToDto).toList();
        return ResponseEntity.ok(list);
    }

    ////////////////////////////////////////////////////

    @GetMapping("/pagination")
    public ResponseEntity<Page<CategoryDTO>> findpage(Pageable pageable){
        Page<CategoryDTO> pageCategoryDTO= service.findPage(pageable).map(this::convertToDto);
        return ResponseEntity.ok(pageCategoryDTO);


    }

    @GetMapping("/pagination2")
    public ResponseEntity<Page<CategoryDTO>> findpage(
            @RequestParam(name="s", defaultValue = "0") int page,
            @RequestParam(name="p",defaultValue = "3") int size
    ){
        Page<CategoryDTO> pageCategoryDTO= service.findPage(PageRequest.of(page,size)).map(this::convertToDto);
        return ResponseEntity.ok(pageCategoryDTO);
    }


    @GetMapping("/order")
        public ResponseEntity<List<CategoryDTO>> findAllOrder(
                @RequestParam(name="param",defaultValue = "ASC") String param)
        {
            List<CategoryDTO> list=service.findAllOrder(param).stream().map(this::convertToDto).toList();
            return ResponseEntity.ok(list);
        }








}
