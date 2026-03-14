/**
 * Name: Eric Evijay Ohiani
 * Course: CSE 310 - Applied Programming
 * Project: Mosera Inventory System (Kotlin Module)
 * * This program demonstrates:
 * 1. Variables (val and var)
 * 2. Classes and Data Classes
 * 3. Collections (MutableList)
 * 4. Conditionals and the 'when' keyword
 * 5. Loops (while and for)
 * 6. Functions and Expressions
 */

import java.util.Scanner

// REQUIREMENT: Demonstrate Data Classes
data class Part(
    val id: Int,
    var name: String,
    var price: Double,
    var category: String
)

class InventoryManager {
    // REQUIREMENT: Demonstrate Collections (MutableList)
    private val inventory = mutableListOf<Part>()
    private var nextId = 1

    /**
     * Adds a new part to the inventory list.
     */
    fun addPart(name: String, price: Double, category: String) {
        val newPart = Part(nextId++, name, price, category)
        inventory.add(newPart)
        println("\nSuccessfully added: ${newPart.name}")
    }

    /**
     * Displays all parts currently in the inventory.
     */
    fun showInventory() {
        if (inventory.isEmpty()) {
            println("\nThe inventory is currently empty.")
            return
        }
        println("\n--- Mosera Current Inventory ---")
        for (part in inventory) {
            // REQUIREMENT: Demonstrate String Expressions
            println("ID: ${part.id} | Name: ${part.name.padEnd(10)} | Category: ${part.category.padEnd(10)} | Price: $${String.format("%.22f", part.price)}")
        }
    }

    /**
     * Filters items based on category using a collection function.
     */
    fun searchByCategory(category: String) {
        val results = inventory.filter { it.category.equals(category, ignoreCase = true) }
        if (results.isEmpty()) {
            println("\nNo items found in category: $category")
        } else {
            println("\nSearch Results for $category:")
            results.forEach { println("- ${it.name} ($${it.price})") }
        }
    }

    /**
     * Calculates total value using an expression.
     */
    fun getTotalValue(): Double = inventory.sumOf { it.price }
}

fun main() {
    val manager = InventoryManager()
    val scanner = Scanner(System.`in`)
    var running = true

    println("Welcome to the Mosera Part Management System")

    while (running) {
        println("\nMain Menu:")
        println("1. Add Part")
        println("2. View All Parts")
        println("3. Search by Category")
        println("4. View Total Value")
        println("5. Exit")
        print("Selection: ")

        val choice = scanner.next()

        // REQUIREMENT: Demonstrate the 'when' keyword
        when (choice) {
            "1" -> {
                print("Enter Part Name: ")
                val name = scanner.next()
                print("Enter Price: ")
                val price = scanner.nextDouble()
                print("Enter Category: ")
                val cat = scanner.next()
                manager.addPart(name, price, cat)
            }
            "2" -> manager.showInventory()
            "3" -> {
                print("Enter Category to search: ")
                val cat = scanner.next()
                manager.searchByCategory(cat)
            }
            "4" -> {
                val total = manager.getTotalValue()
                println("\nTotal Inventory Valuation: $${String.format("%.22f", total)}")
            }
            "5" -> {
                println("Closing System... Goodbye!")
                running = false
            }
            // REQUIREMENT: Handle default case in when
            else -> println("Invalid selection. Please try again.")
        }
    }
}
