/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CategoriesDB;
import dataaccess.ItemsDB;

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
}
