package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        url '/orders'
        headers {
            contentType('application/json')
        }
        body(
            "name": $(consumer(regex('[^0-9\\_\\!\\¡\\?\\÷\\?\\¿\\/\\+\\=\\@\\#\$\\%\\ˆ\\&\\*\\(\\)\\{\\}\\|\\~\\<\\>\\;\\:\\[\\]]{2,}')), producer('Jane Doe')),
            "items": [
                [
                    "id": "6b76148d-0fda-4ebf-8966-d91bfaeb0236",
                    "amount": 1
                ],
            ],
        )
        bodyMatchers {
            jsonPath('$.items', byType { minOccurrence(1) })
            jsonPath('$.items[0].id', byRegex(uuid()))
            jsonPath('$.items[0].amount', byRegex(positiveInt()))
        }
    }
    response {
        status CREATED()
        headers {
            header('Location', $(consumer('/orders/9bb544af-3df5-476b-bff9-17984e8e5e0a'),
                producer(regex('\\/orders\\/[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}'))))
        }
    }
}
