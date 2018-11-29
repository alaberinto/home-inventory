/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CategoriesDB;
import dataaccess.ItemsDB;
import datamodels.Category;
import datamodels.Item;
import java.util.List;

/**
 *
 * @author 587568
 */
public class Inventory
{
    private final ItemsDB itemsDB;
    private final CategoriesDB categoriesDB;
    
    public Inventory()
    {
        itemsDB = new ItemsDB();
        categoriesDB = new CategoriesDB();
    }

    public List<Item> getAllItems() throws Exception
    {
        return itemsDB.getAllItems();
    }

    public List<Category> getAllCategories() throws Exception
    {
        return categoriesDB.getAllCategories();
    }
    
    public Category getCategory(String name) throws Exception
    {
        return categoriesDB.getCategory(name);
    }
    
    public int insert(Item toAdd) throws Exception
    {
        return itemsDB.insert(toAdd);
    }
}
