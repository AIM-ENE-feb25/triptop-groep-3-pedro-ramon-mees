curl --request GET \
	--url 'https://booking-com15.p.rapidapi.com/api/v1/hotels/searchHotelsByCoordinates?latitude=19.24232736426361&longitude=72.85841985686734&arrival_date=2025-03-31&departure_date=2025-04-15&adults=1&room_qty=1&units=metric&page_number=1&temperature_unit=c&languagecode=en-us&currency_code=EUR&location=NL' \
	--header 'x-rapidapi-host: booking-com15.p.rapidapi.com' \
	--header 'x-rapidapi-key: 01fcc8ef7dmsh9371396917f2b4fp187e1ejsn778fecdf4de9' \
	-o response.json
	cat response.json

	read "sssss"