package org.jalcantararivera.mitosales.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jalcantararivera.mitosales.dto.ProductDTO;
import org.jalcantararivera.mitosales.dto.GenericResponse;
import org.jalcantararivera.mitosales.model.Product;
import org.jalcantararivera.mitosales.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor

public class ProductController {

    //private final ProductServiceImpl service;
    private final IProductService service;
    @Qualifier("defaultMapper")
    private final ModelMapper modelMapper;
    //INYECCION CON AUTOWIRED
    //@Autowired
    //private ProductService service;

    //INYECCION POR CONSTRUCTOR
    /*
    public ProductController(ProductService service){

        this.service=service;
    }
    */
    /*
    @GetMapping
    public Product getProductSimple(){
        //service= new ProductService();
        return service.validateProduct(new Product(1,"TV","Television",true));
    }
    */
    @GetMapping
    public ResponseEntity<List<ProductDTO>>  readAll() throws  Exception{
        //ModelMapper modelMapper= new ModelMapper();
        List<ProductDTO>list=service.readAll().stream().map(this::convertToDto).toList();
        /*List<ProductRecord>list2=service.readAll().stream().map(e->new ProductRecord(
                        e.getIdProduct(),
                        e.getName(),
                        e.getDescription(),
                        e.isEnabled())).
                        toList();
        */
        return ResponseEntity.ok(list);
    }

    /*
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Product obj= service.readById(id);
        return ResponseEntity.ok(convertToDto(obj));
    }
    */
    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<ProductDTO>> readById(@PathVariable("id") Integer id) throws Exception{
        Product obj= service.readById(id);
        return ResponseEntity.ok(new GenericResponse<>(200,"SUCCESS", Arrays.asList(convertToDto(obj))));
    }
    @PostMapping
    public ResponseEntity<ProductDTO> save(@Valid @RequestBody ProductDTO dto) throws  Exception{
        Product obj= service.save(convertToEntity(dto));
        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
        //return new ResponseEntity.created();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO dto,@PathVariable("id") Integer id ) throws Exception{
        Product obj= service.update(convertToEntity(dto),id);
        return ResponseEntity.ok(convertToDto(obj));
     }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id ) throws Exception{
        service.delete(id);
        //return ResponseEntity.noContent().build();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    ////////////////////////////////////////////////////
    private ProductDTO convertToDto(Product obj){
        return modelMapper.map(obj,ProductDTO.class);
    }
    private Product convertToEntity(ProductDTO dto){
        return modelMapper.map(dto,Product.class);
    }


}
