curl -i -X POST \
  https://demandapi.booking.com/3.1/common/locations/cities \
  -H 'Authorization: Bearer <YOUR_string_HERE>' \
  -H 'Content-Type: application/json' \
  -H 'X-Affiliate-Id: 0' \
  -d '{
    "country": "nl",
    "languages": [
      "nl-nl",
    ]
  }'