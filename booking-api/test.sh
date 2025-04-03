HOST='booking-com15.p.rapidapi.com'
KEY="47aa077e79msh3aa641c74b24ecep1c9291jsned53c1e5db74"

curl -X GET \
	"https://$HOST/api/v1/test" \
	-H "x-rapidapi-host: $HOST" \
	-H "x-rapidapi-key: $KEY"
