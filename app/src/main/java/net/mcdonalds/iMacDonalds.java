package net.mcdonalds;

public interface iMacDonalds {
   /* @GET("/api/apps/version")
    @Headers({"Content-Type: application/json"})
    Call<Boolean> checkAppVersion(@Query("clientId") String str, @Query("version") String str2);

    @GET("/api/popin/doyouknow")
    @Headers({"Content-Type: application/json"})
    Call<DoYouKnow> retrieveDoYouKnow();

    @GET("/api/catalog/gomcdo")
    @Headers({"Content-Type: application/json"})
    Call<Catalog> retrieveGomcdoCatalog(@Query("restaurantRef") String str, @Query("eatType") String str2, @Query("responseGroups") List<String> list);

    @GET("/api/category/{categoryRef}")
    @Headers({"Content-Type: application/json"})
    Call<Category> retrieveGomcdoCategory(@Path("categoryRef") String str, @Query("restaurantRef") String str2, @Query("eatType") String str3, @Query("responseGroups") List<String> list);

    @GET("/api/catalog/mccafe")
    @Headers({"Content-Type: application/json"})
    Call<Category> retrieveGomcdoMcCafe(@Query("restaurantRef") String str, @Query("eatType") String str2, @Query("responseGroups") List<String> list);

    @GET("/api/catalog/mccafeonline")
    @Headers({"Content-Type: application/json"})
    Call<Category> retrieveGomcdoMcCafeOnline(@Query("restaurantRef") String str, @Query("eatType") String str2, @Query("responseGroups") List<String> list);

    @GET("/api/catalog/suggestions_bo")
    @Headers({"Content-Type: application/json"})
    Call<Catalog> retrieveGomcdoMcSuggestion(@Query("restaurantRef") String str, @Query("eatType") String str2, @Query("responseGroups") List<String> list);

    @GET("/api/product/{product_ref}")
    @Headers({"Content-Type: application/json"})
    Call<Product> retrieveGomcdoProduct(@Path("product_ref") String str, @Query("restaurantRef") String str2);

    @GET("/api/product/{product_ref}")
    @Headers({"Content-Type: application/json"})
    Call<Product> retrieveGomcdoProductChoices(@Path("product_ref") String str, @Query("restaurantRef") String str2, @Query("eatType") String str3, @Query("responseGroups") List<String> list);

    @GET("/api/product/{product_ref}")
    @Headers({"Content-Type: application/json"})
    Call<Product> retrieveGomcdoProductDetails(@Path("product_ref") String str, @Query("responseGroups") List<String> list);

    @GET("/api/catalog/{category_id}/products")
    @Headers({"Content-Type: application/json"})
    Call<List<Product>> retrieveGomcdoProducts(@Path("category_id") String str, @Query("restaurantRef") String str2, @Query("eatType") String str3, @Query("responseGroups") List<String> list);

    @GET("/api/product/")
    @Headers({"Content-Type: application/json"})
    Call<List<Product>> retrieveGomcdoProducts(@Query("productRef") List<String> list, @Query("restaurantRef") String str, @Query("eatType") String str2, @Query("responseGroups") List<String> list2);

    @GET("/api/catalog/gomcdo/firstlevel")
    @Headers({"Content-Type: application/json"})
    Call<List<Category>> retrieveGomcdoRubrics(@Query("restaurantRef") String str, @Query("eatType") String str2, @Query("responseGroups") List<String> list);

    @GET("/api/catalog/{category_id}/children")
    @Headers({"Content-Type: application/json"})
    Call<List<Category>> retrieveGomcdoSubcategories(@Path("category_id") String str, @Query("restaurantRef") String str2, @Query("eatType") String str3, @Query("responseGroups") List<String> list);

    @GET("/api/legalNotice/find")
    @Headers({"Content-Type: application/json"})
    Call<LegalNotice> retrieveLegalNotice(@Query("displayType") String str);

    @GET("/api/multimedia/get_news")
    @Headers({"Content-Type: application/json"})
    Call<List<NewsParent>> retrieveNews();

    @GET("/api/multimedia/get_news/all")
    @Headers({"Content-Type: application/json"})
    Call<List<NewsParent>> retrieveNewsAll();

    @GET("/api/catalog/originals")
    @Headers({"Content-Type: application/json"})
    Call<Catalog> retrieveOriginalsCatalog(@Query("restaurantRef") String str, @Query("eatType") String str2, @Query("responseGroups") List<String> list);

    @GET("/api/promotion/{promotionRef}")
    @Headers({"Content-Type: application/json"})
    Call<Promotion> retrievePromotion(@Path("promotionRef") String str, @Query("customerRef") String str2, @Query("restaurantRef") String str3, @Query("eatType") String str4, @Query("responseGroups") List<String> list);

    @GET("/api/promotion/today")
    @Headers({"Content-Type: application/json"})
    Call<List<Promotion>> retrievePromotionsToday(@Query("customerRef") String str, @Query("restaurantRef") String str2, @Query("eatType") String str3, @Query("responseGroups") List<String> list);

    @GET("/api/restaurant/{ref}")
    @Headers({"Content-Type: application/json"})
    Call<Restaurant> retrieveRestaurant(@Path("ref") String str, @Query("responseGroups") List<String> list);

    @GET("/api/restaurant")
    @Headers({"Content-Type: application/json"})
    Call<List<Restaurant>> retrieveRestaurants(@Query("restaurantRef") List<String> list, @Query("responseGroups") List<String> list2);
*/
}
