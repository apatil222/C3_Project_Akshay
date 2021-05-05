import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    private void mockData()
    {
        LocalTime openingTime = LocalTime.parse("11:30:00");
        LocalTime closingTime = LocalTime.parse("24:00:00");
        restaurant =new Restaurant("Beach cafe","New York",openingTime,closingTime);
        restaurant.addToMenu("Hamburger",35,true);
        restaurant.addToMenu("Pizza", 12,false);
    }
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        mockData();
        LocalTime currentTime = LocalTime.parse("15:45:00");
        boolean restaurantStatus = restaurant.isRestaurantOpen(currentTime);
        assertTrue(restaurantStatus);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        mockData();
        LocalTime currentTime = LocalTime.parse("22:45:00");
        boolean restaurantStatus = restaurant.isRestaurantOpen(currentTime);
        assertFalse(restaurantStatus);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        LocalTime openingTime = LocalTime.parse("11:00:00");
        LocalTime closingTime = LocalTime.parse("24:00:00");
        restaurant =new Restaurant("Beach cafe","New York",openingTime,closingTime);
        restaurant.addToMenu("Hamburger",35);
        restaurant.addToMenu("Pizza", 12);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Milk shake",6);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        LocalTime openingTime = LocalTime.parse("11:00:00");
        LocalTime closingTime = LocalTime.parse("24:00:00");
        restaurant =new Restaurant("Beach cafe","New York",openingTime,closingTime);
        restaurant.addToMenu("Hamburger",35);
        restaurant.addToMenu("Pizza", 12);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Pizza");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        LocalTime openingTime = LocalTime.parse("11:00:00");
        LocalTime closingTime = LocalTime.parse("24:00:00");
        restaurant =new Restaurant("Beach cafe","New York",openingTime,closingTime);
        restaurant.addToMenu("Hamburger",35);
        restaurant.addToMenu("Pizza", 12);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("Coke"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}