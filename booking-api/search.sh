HOST='booking-com15.p.rapidapi.com'
KEY="47aa077e79msh3aa641c74b24ecep1c9291jsned53c1e5db74"

START_DATE="2025-03-24"
END_DATE="2025-03-25"

curl -X GET \
	"https://$HOST/api/v1/hotels/searchHotels?dest_id=-2140612&search_type=CITY&arrival_date=$START_DATE&departure_date=$END_DATE&adults=1&children_age=0%2C17&room_qty=1&page_number=1&units=metric&temperature_unit=c&languagecode=nl&currency_code=EUR" \
	-H "x-rapidapi-host: $HOST" \
	-H "x-rapidapi-key: $KEY"

