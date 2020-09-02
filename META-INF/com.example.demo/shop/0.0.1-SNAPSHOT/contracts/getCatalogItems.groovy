package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url '/catalog/items'
        headers {
            accept('application/json')
        }
    }
    response {
        status OK()
        headers {
            contentType('application/json')
        }
        body([
            [
                "id": "6b76148d-0fda-4ebf-8966-d91bfaeb0236",
                "name": "Breakfast with homemade bread",
                "img": "https://images.unsplash.com/photo-1590688178590-bb8370b70528",
                "price": 16,
            ],
            [
                "id": "52d59380-79da-49d5-9d09-9716e20ccbc4",
                "name": "Brisket",
                "img": "https://images.unsplash.com/photo-1592894869086-f828b161e90a",
                "price": 24,
            ],
            [
                "id": "a7be01f8-b76e-4384-bf1d-e69d7bdbe4b4",
                "name": "Pork Ribs",
                "img": "https://images.unsplash.com/photo-1544025162-d76694265947",
                "price": 20,
            ],
        ])
        bodyMatchers {
            jsonPath('$', byType { minOccurrence(1) })
            jsonPath('$[*].id', byRegex(uuid()))
            jsonPath('$[*].name', byRegex("[a-zA-Z \\-]+"))
            jsonPath('$[*].img', byRegex(url()))
            jsonPath('$[*].price', byRegex(positiveInt()))
        }
    }
}
