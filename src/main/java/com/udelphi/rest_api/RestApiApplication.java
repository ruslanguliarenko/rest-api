package com.udelphi.rest_api;

import com.udelphi.rest_api.dto.CategoryDto;
import com.udelphi.rest_api.dto.ProductDto;
import com.udelphi.rest_api.dto.RoleDto;
import com.udelphi.rest_api.dto.UserDto;
import com.udelphi.rest_api.model.*;
import com.udelphi.rest_api.model.id.ProductCategoryId;
import com.udelphi.rest_api.model.id.UserRoleId;
import static java.util.stream.Collectors.toSet;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RestApiApplication {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(new Converter<Product, ProductDto>() {
            @Override
            public ProductDto convert(MappingContext<Product, ProductDto> mappingContext) {
                Product product = mappingContext.getSource();
                ProductDto productDto = new ProductDto();

                productDto.setId(product.getId());
                productDto.setName(product.getName());
                productDto.setDescription(product.getDescription());
                productDto.setPrice(product.getPrice());

                productDto.setCategories(
                        product.getCategories()
                                .stream()
                                .map(ProductCategory::getCategory)
                                .map(category -> modelMapper.map(category, CategoryDto.class))
                                .collect(toSet())
                );

                return productDto;
            }
        });


        modelMapper.addConverter(new Converter<ProductDto, Product>() {
            @Override
            public Product convert(MappingContext<ProductDto, Product> mappingContext) {
                ProductDto productDto = mappingContext.getSource();
                Product product = new Product();

                product.setId(productDto.getId());
                product.setName(productDto.getName());
                product.setDescription(productDto.getDescription());
                product.setPrice(productDto.getPrice());

                product.setCategories(
                        productDto.getCategories()
                                .stream()
                                .map(categoryDto -> modelMapper.map(categoryDto, Category.class))
                                .map(category -> new ProductCategory(new ProductCategoryId(product.getId(), category.getId()), product, category))
                                .collect(toSet())
                );

                return product;
            }
        });
        modelMapper.addConverter(new Converter<User, UserDto>() {
            @Override
            public UserDto convert(MappingContext<User, UserDto> mappingContext) {
                User user = mappingContext.getSource();
                UserDto userDto = new UserDto();

                userDto.setId(user.getId());
                userDto.setName(user.getName());
                userDto.setEmail(user.getEmail());

                userDto.setRoles(
                        user.getRoles()
                                .stream()
                                .map(UserRole::getRole)
                                .map(role -> modelMapper.map(role, RoleDto.class))
                                .collect(toSet())
                );

                return userDto;
            }
        });


        modelMapper.addConverter(new Converter<UserDto, User>() {
            @Override
            public User convert(MappingContext<UserDto, User> mappingContext) {
                UserDto userDto = mappingContext.getSource();
                User user = new User();

                user.setId(userDto.getId());
                user.setName(userDto.getName());
                user.setEmail(userDto.getEmail());

                user.setRoles(
                        userDto.getRoles()
                                .stream()
                                .map(roleDto -> modelMapper.map(roleDto, Role.class))
                                .map(role -> new UserRole(new UserRoleId(user.getId(), role.getId()), user, role))
                                .collect(toSet())
                );

                return user;
            }
        });

        return modelMapper;
    }
    public static void main(String[] args) {

        SpringApplication.run(RestApiApplication.class, args);
    }

}
