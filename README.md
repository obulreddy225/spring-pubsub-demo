GCP PUB/SUB
Create a GCP Project——
gcloud projects create my-otp-project-obul225
whenever you need to refer to your project———
gcloud config set project my-otp-project-obul225
Enable the Pub/Sub API—————
gcloud services enable pubsub.googleapis.com --project=my-otp-project-obul225
Create a Pub/Sub Topic————
gcloud pubsub topics create otp-topic --project=my-otp-project-obul225
Create a Subscription————
gcloud pubsub subscriptions create otp-subscription \
  --topic=otp-topic \
  --project=my-otp-project-obul225
Create a Service Account——————————
A service account is needed for your Spring Boot app to authenticate with Pub/Sub.—————

gcloud iam service-accounts create otp-service-account \
  --display-name="OTP PubSub Service Account" \
  --project=my-otp-project-obul225
Give it Pub/Sub permissions—————
gcloud projects add-iam-policy-binding my-otp-project-obul225 \
  --member="serviceAccount:otp-service-account@my-otp-project-obul225.iam.gserviceaccount.com" \
  --role="roles/pubsub.publisher"

gcloud projects add-iam-policy-binding my-otp-project-obul225 \
  --member="serviceAccount:otp-service-account@my-otp-project-obul225.iam.gserviceaccount.com" \
  --role="roles/pubsub.subscriber"
Generate a JSON key———————
gcloud iam service-accounts keys create ~/otp-service-account-key.json \
  --iam-account=otp-service-account@YOUR_PROJECT_ID.iam.gserviceaccount.com









