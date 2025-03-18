USER="edevries"
PASS="3g2Rw9sT1x"
TOKEN="aedc5e36-882a-4b59-a9f6-629d5ceaa391"
APP="triptop"

auth() {
curl \
	 -X POST \
	 "https://triptop-identity.wiremockapi.cloud/login" \
	 -H "Content-Type: application/json" \
	 -d '{"username": "'"$USER"'", "password": "'"$PASS"'"}'
}

check() {
curl \
	 -X POST \
	 "https://triptop-identity.wiremockapi.cloud/checkAppAccess?token=$TOKEN" \
	 -H "Content-Type: application/json" \
	 -d '{"username": "'"$USER"'", "application": "'"$APP"'"}'
}

#auth
#check
