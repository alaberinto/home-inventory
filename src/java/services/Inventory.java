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
    
    public int insertCategory(Category toAdd) throws Exception
    {
        return categoriesDB.insert(toAdd);
    }
    
    public int insertItem(Item toAdd) throws Exception
    {
        return itemsDB.insert(toAdd);
    }

    public Item getItem(String selected) throws Exception
    {
        int id = Integer.parseInt(selected);
        return itemsDB.getItem(id);
    }

    public int updateItem(Item toEdit, String sessionUsername) throws Exception
    {
        if(!toEdit.getOwner().getUsername().equals(sessionUsername))
        {
            return 0;
        }
        return itemsDB.update(toEdit);
    }

    public int deleteItem(Item toDelete, String sessionUsername) throws Exception
    {
        if(!toDelete.getOwner().getUsername().equals(sessionUsername))
        {
            return 0;
        }
        return itemsDB.delete(toDelete);
    }
}
