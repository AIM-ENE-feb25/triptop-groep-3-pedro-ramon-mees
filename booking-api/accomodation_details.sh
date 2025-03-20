curl -i -X POST \
  https://demandapi.booking.com/3.1/accommodations/details \
  -H 'Authorization: Bearer <YOUR_string_HERE>' \
  -H 'Content-Type: application/json' \
  -H 'X-Affiliate-Id: 0' \
  -d '{
    "accommodations": [
      10004
    ],
    "extras": [
    ],
    "languages": [
      "nl-nl",
    ]
  }'