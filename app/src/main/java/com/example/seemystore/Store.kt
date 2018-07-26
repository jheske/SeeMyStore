package com.example.seemystore

/**
 * @author Jill Heske
 *
 */

/*
{
    "stores": [
    {
        "address": "7801 Citrus Park Town Center Mall",
        "city": "Tampa",
        "name": "Macy's",
        "latitude": "28.068052",
        "zipcode": "33625",
        "storeLogoURL": "http://sandbox.bottlerocketapps.com/BR_Android_CodingExam_2015_Server/images/macys.jpeg",
        "phone": "813-926-7300",
        "longitude": "-82.573301",
        "storeID": "1234",
        "state": "FL"
    },
    {
        "address": "27002 US Highway 19 N",
        "city": "Clearwater",
        "name": "Nordstrom",
        "latitude": "27.9998988",
        "zipcode": "33761",
        "storeLogoURL": "http://sandbox.bottlerocketapps.com/BR_Android_CodingExam_2015_Server/images/nordstrom.jpeg",
        "phone": "727-296-2243",
        "longitude": "-82.7295986",
        "storeID": "1237",
        "state": "FL"
    },
    {
        "address": "28001 US Highway 19 N",
        "city": "Clearwater",
        "name": "Bling Bling",
        "latitude": "28.005",
        "zipcode": "33761",
        "storeLogoURL": "http://sandbox.bottlerocketapps.com/BR_Android_CodingExam_2015_Server/images/foleys.jpeg",
        "phone": "727-296-2252",
        "longitude": "-82.7296806",
        "storeID": "1255",
        "state": "FL"
    }
    ]
}

*/

data class Store(var storeLogoURL: String,
            var storeID: String,
            var name: String,
            var address: String,
            var city: String,
            var state: String,
            var zipcode: String,
            var phone: String,
            var latitude: Double,
            var longitude: Double)





