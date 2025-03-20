curl -i -X POST \
  https://demandapi.booking.com/3.1/accommodations/availability \
  -H 'Authorization: Bearer <YOUR_string_HERE>' \
  -H 'Content-Type: application/json' \
  -H 'X-Affiliate-Id: 0' \
  -d '{
    "accommodation": 10004,
    "booker": {
      "country": "nl",
      "platform": "desktop"
    },
    "checkin": "!START_DATE!",
    "checkout": "!END_DATE!",
    "extras": [
      "extra_charges"
    ],
    "guests": {
      "number_of_adults": 2,
      "number_of_rooms": 1
    }
  }'