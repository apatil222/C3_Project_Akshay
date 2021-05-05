import org.junit.jupiter.api.*;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    private void mockData()
    {
        LocalTime openingTime = LocalTime.parse("11:30:00");
        LocalTime closingTime = LocalTime.parse("24:00:00");
        restaurant =new Restaurant("Beach cafe","New York",openingTime,closingTime);
        restaurant.addToMenu("Hamburger",35,true);
        restaurant.addToMenu("Pizza", 12,false);
    }

    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        mockData();
        List<Restaurant> getRestaurants = service.getRestaurants();
        Restaurant getRestaurant = service.findRestaurantByName("Beach cafe");
        assertEquals(getRestaurants.get(0),getRestaurant);
    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        assertThrows(restaurantNotFoundException.class,()->service.findRestaurantByName("Pantry d'or"));
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        LocalTime openingTime = LocalTime.parse("11:30:00");
        LocalTime closingTime = LocalTime.parse("24:00:00");
        restaurant =new Restaurant("Beach cafe","New York",openingTime,closingTime);
        restaurant.addToMenu("Hamburger",35,true);
        restaurant.addToMenu("Pizza", 12,false);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Beach cafe");
        assertEquals(initialNumberOfRestaurants-1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        LocalTime openingTime = LocalTime.parse("11:30:00");
        LocalTime closingTime = LocalTime.parse("24:00:00");
        restaurant =new Restaurant("Beach cafe","New York",openingTime,closingTime);
        restaurant.addToMenu("Hamburger",35,true);
        restaurant.addToMenu("Pizza", 12,false);

        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        LocalTime openingTime = LocalTime.parse("11:30:00");
        LocalTime closingTime = LocalTime.parse("24:00:00");
        restaurant =new Restaurant("Beach cafe","New York",openingTime,closingTime);
        restaurant.addToMenu("Hamburger",35,true);
        restaurant.addToMenu("Pizza", 12,false);

        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Seven Hills","Buffalo",LocalTime.parse("11:00:00"),LocalTime.parse("24:00:00"));
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void calculate_final_order_value_pass() {
        addFoodItemsToCart();

        Object orderValue;
        assertEquals(1215, orderValue.totalOrderValue());
    }

    @Test
    public void calculate_final_order_value_fail() {
        addFoodItemsToCart();

        Object orderValue;
        assertNotEquals(100, orderValue.totalOrderValue());
    }

    private void addFoodItemsToCart() {
        Item Burger = new Item("Burger", 4, true);
        orderValue.addFoodItemsToCart(Burger, 1);

        Item Salad = new Item("Salad", 10, true);
        orderValue.addFoodItemsToCart(Salad, 1);

        Item Spicy_Curry = new Item("Spicy Curry", 15, true);
        orderValue.addFoodItemsToCart(Spicy_Curry, 1);
        
    }
}
}