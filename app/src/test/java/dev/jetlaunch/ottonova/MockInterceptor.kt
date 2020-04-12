package dev.jetlaunch.ottonova

import okhttp3.*

class MockInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url().uri().toString()
        val responseString = when {
            uri.endsWith("profiles") -> profileResponse
            uri.endsWith("timeline-events") -> timeLineResponse
            uri.endsWith("health-prompts") -> healthPromptResponse
            else -> ""
        }

        return chain.proceed(chain.request())
            .newBuilder()
            .code(200)
            .protocol(Protocol.HTTP_2)
            .message(responseString)
            .body(
                ResponseBody.create(
                    MediaType.parse("application/json"),
                responseString.toByteArray()))
            .addHeader("content-type", "application/json")
            .build()
    }

    val profileResponse = """[
  {
    "profile_id": "freemium_profile",
    "first_name": "Otto",
    "last_name": "Nova",
    "gender": "male",
    "display_name": "Otto Nova",
    "is_primary_profile": true,
    "policy_number": "000000001",
    "tariff_id": "chi-first",
    "tariff_label": "First Class",
    "tariff": {
      "id": "chi-first",
      "label": "First Class",
      "icon": {
        "url": "https://freemium.ottonova.de/api/images/tariffs/tariff_freemium_first_class.png",
        "primary_color": "#667093",
        "secondary_color": "#999FB7"
      },
      "excess_rate": 10
    },
    "deductible": {
      "total": 50000,
      "remaining": 27050
    },
    "date_of_birth": "1989-01-01",
    "address": {
      "street": "Ottostr.",
      "street_number": "4",
      "zip": "80333",
      "city": "München"
    },
    "contact": {
      "email": "feedback@ottonova.app",
      "phone": "089 12 14 07 12"
    },
    "profile_attributes": [
      "freemium",
      "signup_complete",
      "appointment_booking_available",
      "health_x_complete"
    ]
  }
]"""

    val healthPromptResponse = """[
  {
    "uuid": "freemium_impuls_201902",
    "message": "Gesundheitsimpuls 02/19\nStell dir heute mal den Wecker zum Schlafengehen.",
    "permanent": true,
    "display_category": "ottonova",
    "metadata": {
      "link": {
        "title": "Mehr zu gesundem Schlaf",
        "url": "https://www.ottonova.de/health/drum-schlaf-auch-du"
      }
    }
  },
  {
    "uuid": "freemium_healthprompt_1",
    "message": "Kostenlose happybrush Zahnbürste sichern mit der Zahnzusatzversicherung.",
    "display_category": "ottonova",
    "permanent": true,
    "metadata": {
      "link": {
        "title": "Tarif ansehen",
        "url": "https://www.ottonova.de/tariffs?id=dental_topup"
      }
    },
    "style": {
      "primary_color": "#9CE2DA",
      "secondary_color": "#5EC1C7",
      "image": "https://freemium.ottonova.de/api/images/health-prompts/happybrush.png"
    }
  },
  {
    "uuid": "freemium_healthprompt_0",
    "message": "Unsere Kunden verfügen über ihre persönliche Gesundheits-Timeline.",
    "display_category": "ottonova",
    "permanent": true,
    "metadata": {
      "link": {
        "title": "Tarife ansehen",
        "url": "https://www.ottonova.de/tariffs"
      }
    }
  }
]"""

    val timeLineResponse = """[
  {
    "uuid": "freemium_timeline_event_3",
    "timestamp": "2020-03-15T09:00:00+00:00",
    "display_category": "pharmacy",
    "title": "Apothekeneinkauf",
    "description": "Die Beste Apotheke",
    "category": "pharmacy_purchase"
  },
  {
    "uuid": "freemium_timeline_event_2",
    "timestamp": "2020-03-15T08:00:00+00:00",
    "display_category": "dentist",
    "title": "Zahnarztbesuch",
    "description": "Dr. Medicus Dent",
    "category": "doctor_visit"
  },
  {
    "uuid": "freemium_timeline_event_1",
    "timestamp": "2020-03-01T08:00:00+00:00",
    "display_category": "ottonova",
    "title": "Vertragsbeginn",
    "description": "Ab jetzt alle Services nutzen",
    "category": "contract_start"
  },
  {
    "uuid": "freemium_timeline_event_0",
    "timestamp": "2020-02-18T08:00:00+00:00",
    "display_category": "ottonova",
    "title": "Willkommen bei ottonova",
    "description": "Vertragsabschluss",
    "category": "welcome"
  }
]"""

}