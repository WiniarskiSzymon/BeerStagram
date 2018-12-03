package com.projects.bigswierku.beerstagram

import com.projects.bigswierku.beerstagram.Api.UntappedAPI
import com.projects.bigswierku.beerstagram.model.untapped.Beer
import io.reactivex.subscribers.TestSubscriber

import org.junit.Before
import org.junit.Rule
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.ContentValues.TAG
import android.util.Log
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.projects.bigswierku.beerstagram.model.untapped.BeerResponse
import com.projects.bigswierku.beerstagram.model.untapped.PubResponse
import io.reactivex.Flowable
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test
import java.io.InputStream


class UntappedApiTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var mockServer : MockWebServer
    private lateinit var gson: Gson
    private lateinit var untappedAPI : UntappedAPI
    private lateinit var localCheckInsResponseMock : MockResponse
    private lateinit var beerinfoResponseMock : MockResponse
    private lateinit var checkInstestSubscriber :TestSubscriber<PubResponse>
    private lateinit var beerInfoSubscriber :TestSubscriber<BeerResponse>

    private val localCheckInsJSON = """{"radius":10,"dist_pref":"m","checkins":{"count":25,"items":{"checkin_id":137117722,"distance":2.568,"created_at":"Sat,13Dec201419:15:38+0000","checkin_comment":"WheninRome..","rating_score":3,"user":{"uid":1,"user_name":"gregavola","first_name":"Greg","last_name":"Avola","location":"NewYork,NY","is_supporter":1,"url":"http://gregavola.com","bio":"Co-FounderandCTOofUntappd,WebDeveloper,BeerDrinker&CommunityGuy","relationship":"self","user_avatar":"https://gravatar.com/avatar/0c6922e238dae5cccce96a32889fc911?size=100&d=htt…44.cloudfront.net%2Fsite%2Fassets%2Fimages%2Fdefault_avatar_v2.jpg%3Fv%3D1","is_private":0,"contact":{"foursquare":195741,"twitter":"gregavola","facebook":18603076}},"beer":{"bid":7481,"beer_name":"BrooklynBowlPaleAle","beer_label":"https://d1c8v1qci5en44.cloudfront.net/site/assets/images/temp/badge-beer-default.png","beer_style":"AmericanPaleAle","beer_abv":0,"auth_rating":0,"wish_list":false,"beer_active":1},"brewery":{"brewery_id":1954,"brewery_name":"KelsoofBrooklyn","brewery_slug":"kelso-of-brooklyn","brewery_label":"https://d1c8v1qci5en44.cloudfront.net/site/brewery_logos/brewery-KelsoofBrooklyn_1954.jpeg","country_name":"UnitedStates","contact":{"twitter":"KelsoBeer","facebook":"","instagram":"","url":"http://www.kelsoofbrooklyn.com/"},"location":{"brewery_city":"Brooklyn","brewery_state":"NY","lat":40.6823,"lng":-73.9656},"brewery_active":1},"venue":{"venue_id":2141,"venue_name":"BrooklynBowl","primary_category":"Arts&Entertainment","parent_category_id":"4d4b7104d754a06370d81259","categories":{"count":3,"items":[{"category_name":"BowlingAlley","category_id":"4bf58dd8d48988d1e4931735","is_primary":true},{"category_name":"MusicVenue","category_id":"4bf58dd8d48988d1e5931735","is_primary":false},{"category_name":"Bar","category_id":"4bf58dd8d48988d116941735","is_primary":false}]},"location":{"venue_address":"61WytheAve","venue_city":"Brooklyn","venue_state":"NY","venue_country":"UnitedStates","lat":40.7219,"lng":-73.9575},"contact":{"twitter":"@brooklynbowl","venue_url":"http://www.brooklynbowl.com"},"public_venue":true,"foursquare":{"foursquare_id":"4a1afeb7f964a520b77a1fe3","foursquare_url":"http://4sq.com/3fjtlA"},"venue_icon":{"sm":"https://ss3.4sqi.net/img/categories_v2/arts_entertainment/bowling_bg_64.png","md":"https://ss3.4sqi.net/img/categories_v2/arts_entertainment/bowling_bg_88.png","lg":"https://ss3.4sqi.net/img/categories_v2/arts_entertainment/bowling_bg_88.png"}},"comments":{"total_count":0,"count":0,"items":[]},"toasts":{"total_count":0,"count":0,"auth_toast":false,"items":[]},"media":{"count":0,"items":[]},"source":{"app_name":"UntappdforiPhone-(V2)","app_website":"http://untpd.it/iphoneapp"},"badges":{"count":1,"items":[{"badge_id":189,"user_badge_id":39410316,"badge_name":"TastetheMusic","badge_description":"BadgeDescriptionHere","created_at":"Sat,13Dec201419:15:41+0000","badge_image":{"sm":"https://d1c8v1qci5en44.cloudfront.net/badges/bdg_ConcertVenue_sm.jpg","md":"https://d1c8v1qci5en44.cloudfront.net/badges/bdg_ConcertVenue_md.jpg","lg":"https://d1c8v1qci5en44.cloudfront.net/badges/bdg_ConcertVenue_lg.jpg"}}]}}}}"""
    private val beerInfoJSON ="""{"beer": {"bid":16630,"beer_name":"CelebrationAle","beer_label":"https://d1c8v1qci5en44.cloudfront.net/site/beer_logos/beer-_16630_sm_96f50e03ae848a4a368a787b38f989.jpeg","beer_abv":6.8,"beer_ibu":65,"beer_description":"The long,coldnightsofwinterarealittlebrighterwithCelebrationAle.Wonderfullyrobustandrich,CelebrationAleisdry-hoppedforalively,intensearoma.Brewedespeciallyfortheholidays,itisperfectforafestivegatheringorforaquieteveningathome.","beer_style":"AmericanIPA","is_in_production":1,"beer_slug":"sierra-nevada-brewing-co-celebration-ale","is_homebrew":0,"created_at":"Fri,24Dec201010:10:42+0000","rating_count":35148,"rating_score":3.78495,"stats":{"total_count":57453,"monthly_count":24387,"total_user_count":39594,"user_count":0},"brewery":{"brewery_id":1142,"brewery_name":"SierraNevadaBrewingCo.","brewery_label":"https://d1c8v1qci5en44.cloudfront.net/site/brewery_logos/brewery-1142_f241d.jpeg","country_name":"UnitedStates","contact":{"twitter":"SierraNevada","facebook":"http://www.facebook.com/sierranevadabeer","url":"http://www.sierranevada.com/"},"location":{"brewery_city":"Chico","brewery_state":"CA","lat":39.7246,"lng":-121.816}},"auth_rating":0,"wish_list":false,"media":{"count":1,"items":{"photo_id":25856365,"photo":{"photo_img_sm":"https://d1c8v1qci5en44.cloudfront.net/photo/2014_12_14/b73895e69761fdb62a4a9e10294e9613_100x100.jpg","photo_img_md":"https://d1c8v1qci5en44.cloudfront.net/photo/2014_12_14/b73895e69761fdb62a4a9e10294e9613_320x320.jpg","photo_img_lg":"https://d1c8v1qci5en44.cloudfront.net/photo/2014_12_14/b73895e69761fdb62a4a9e10294e9613_640x640.jpg","photo_img_og":"https://d1c8v1qci5en44.cloudfront.net/photo/2014_12_14/b73895e69761fdb62a4a9e10294e9613_raw.jpg"},"created_at":"Sun,14Dec201422:39:26+0000","checkin_id":137623689,"beer":{"bid":16630,"beer_name":"CelebrationAle","beer_label":"https://d1c8v1qci5en44.cloudfront.net/site/beer_logos/beer-_16630_sm_96f50e03ae848a4a368a787b38f989.jpeg","beer_abv":6.8,"beer_ibu":65,"beer_slug":"sierra-nevada-brewing-co-celebration-ale","beer_description":"Thelong,coldnightsofwinterarealittlebrighterwithCelebrationAle.Wonderfullyrobustandrich,CelebrationAleisdry-hoppedforalively,intensearoma.Brewedespeciallyfortheholidays,itisperfectforafestivegatheringorforaquieteveningathome.","is_in_production":1,"beer_style_id":128,"beer_style":"AmericanIPA","auth_rating":0,"wish_list":false,"beer_active":1},"brewery":{"brewery_id":1142,"brewery_name":"SierraNevadaBrewingCo.","brewery_slug":"sierra-nevada-brewing-co","brewery_label":"https://d1c8v1qci5en44.cloudfront.net/site/brewery_logos/brewery-1142_f241d.jpeg","country_name":"UnitedStates","contact":{"twitter":"SierraNevada","facebook":"http://www.facebook.com/sierranevadabeer","url":"http://www.sierranevada.com/"},"location":{"brewery_city":"Chico","brewery_state":"CA","lat":39.7246,"lng":-121.816},"brewery_active":1},"user":{"uid":1068060,"user_name":"Sallyddrake","first_name":"Sally","last_name":"D","user_avatar":"https://gravatar.com/avatar/51d0f47cf4633afac7dda8990a67ab1b?size=100&d=htt…44.cloudfront.net%2Fsite%2Fassets%2Fimages%2Fdefault_avatar_v2.jpg%3Fv%3D1","relationship":"none","is_private":0},"venue":[{"venue_id":1922107,"venue_name":"Lucky'sMarket","primary_category":"Shop&Service","parent_category_id":"4d4b7105d754a06378d81259","categories":{"count":1,"items":[{"category_name":"GroceryorSupermarket","category_id":"4bf58dd8d48988d118951735","is_primary":true}]},"location":{"venue_address":"FountainPlaza","venue_city":"Ellisville","venue_state":"MO","lat":38.6058,"lng":-90.5834},"contact":{"twitter":"","venue_url":""},"private_venue":true,"foursquare":{"foursquare_id":"53d80e2b498eb7cff03ec47a","foursquare_url":"http://4sq.com/1rCS43U"},"venue_icon":{"sm":"https://ss3.4sqi.net/img/categories_v2/shops/food_grocery_bg_64.png","md":"https://ss3.4sqi.net/img/categories_v2/shops/food_grocery_bg_88.png","lg":"https://ss3.4sqi.net/img/categories_v2/shops/food_grocery_bg_88.png"}}]}},"similar":{"count":1,"items":{"rating_score":4.16096,"beer":{"bid":881386,"beer_name":"StoneEnjoyBy12.26.14IPA","beer_abv":9.4,"beer_ibu":88,"beer_style":"Imperial/DoubleIPA","beer_label":"https://d1c8v1qci5en44.cloudfront.net/site/beer_logos/beer-881386_1a85e_sm.jpeg","auth_rating":0,"wish_list":false},"brewery":{"brewery_id":1204,"brewery_name":"StoneBrewingCo.","brewery_slug":"stone-brewing-co","brewery_label":"https://d1c8v1qci5en44.cloudfront.net/site/brewery_logos/brewery-stone.jpg","country_name":"UnitedStates","contact":{"twitter":"StoneBrewingCo","facebook":"http://www.facebook.com/StoneBrewingCo","instagram":"StoneBrewingCo","url":"http://www.stonebrew.com/"},"location":{"brewery_city":"Escondido","brewery_state":"CA","lat":33.1157,"lng":-117.12},"brewery_active":1},"friends":{"items":[],"count":0}}},"friends":{"count":0,"items":[]},"vintages":{"count":5,"items":[{"beer":{"bid":6796,"beer_label":"https://d1c8v1qci5en44.cloudfront.net/site/beer_logos/beer-_6796_sm_7a8d7db0099654386f616e26ccb043.jpeg","beer_slug":"sierra-nevada-brewing-co-celebration-ale-2010","beer_name":"CelebrationAle(2010)","is_vintage":1,"is_variant":0}},{"beer":{"bid":10611,"beer_label":"https://d1c8v1qci5en44.cloudfront.net/site/beer_logos/beer-_10611_sm_f9e846389a5b26bb4888557aa78a29.jpeg","beer_slug":"sierra-nevada-brewing-co-celebration-ale-2007","beer_name":"CelebrationAle(2007)","is_vintage":1,"is_variant":0}},{"beer":{"bid":12371,"beer_label":"https://d1c8v1qci5en44.cloudfront.net/site/beer_logos/beer-_12371_sm_389b71c2126a12d3e538ba9d0ef82e.jpeg","beer_slug":"sierra-nevada-brewing-co-celebration-ale-2009","beer_name":"CelebrationAle(2009)","is_vintage":1,"is_variant":0}},{"beer":{"bid":15030,"beer_label":"https://d1c8v1qci5en44.cloudfront.net/site/beer_logos/beer-_16630_sm_96f50e03ae848a4a368a787b38f989.jpeg","beer_slug":"sierra-nevada-brewing-co-celebration-ale-1997","beer_name":"CelebrationAle(1997)","is_vintage":1,"is_variant":0}},{"beer":{"bid":19893,"beer_label":"https://d1c8v1qci5en44.cloudfront.net/site/beer_logos/beer-_16630_sm_96f50e03ae848a4a368a787b38f989.jpeg","beer_slug":"sierra-nevada-brewing-co-celebration-ale-2006","beer_name":"CelebrationAle(2006)","is_vintage":1,"is_variant":0}}]}}}"""
//        private val localCheckInsJSON = String().fromJSONFile()
//        private val beerInfoJSON =
    @Before
    fun setUp() {
        untappedAPI=UntappedAPI()
        MockitoAnnotations.initMocks(this)
        checkInstestSubscriber = TestSubscriber()
        beerInfoSubscriber = TestSubscriber()
        gson = Gson()
        mockServer = MockWebServer()
        mockServer.start()
        localCheckInsResponseMock = MockResponse()
                .setResponseCode(200)
                .setBody(localCheckInsJSON)
        beerinfoResponseMock = MockResponse()
                .setResponseCode(200)
                .setBody(beerInfoJSON)

    }

    @Test
    fun getLocalCheckInsTest(){
        mockServer.enqueue(beerinfoResponseMock)
        untappedAPI.getCheckIns().subscribe(checkInstestSubscriber)

        checkInstestSubscriber.assertNoErrors()
        checkInstestSubscriber.assertValueCount(1)
        checkInstestSubscriber.assertValue(gson.fromJson(beerInfoJSON, PubResponse::class.java))

    }

    @Test
    fun getBeerInfoTest(){
        mockServer.enqueue(localCheckInsResponseMock)
        untappedAPI.getBeerInfo().subscribe(beerInfoSubscriber)

        beerInfoSubscriber.assertNoErrors()
        beerInfoSubscriber.assertValueCount(1)
        beerInfoSubscriber.assertValue(gson.fromJson(localCheckInsJSON, BeerResponse::class.java))

    }




}

