package com.example.brawlmobile.remote

import android.content.Context
import com.example.brawlmobile.R
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.KeyStore
import java.security.cert.CertificateFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager


/**
 * Questa classe permette di creare un'istanza di Retrofit configurata per effettuare richieste all'API remota specificata
 * tramite l'URL di base BASE_URL. Utilizza un OkHttpClient personalizzato per accettare un certificato del server personalizzato,
 * il quale viene caricato da una risorsa locale.
 */
class RemoteApi(
    private val context: Context
) {

    private val BASE_URL = "https://34.154.196.182/api/"

    companion object {
        // Metodo di factory per creare una istanza di RemoteApi
        fun create(context: Context): RemoteApi {
            return RemoteApi(context)
        }
    }

    // Il client OkHttpClient utilizzato per effettuare le richieste HTTP
    private var client: OkHttpClient? = null

    // Metodo privato per ottenere un OkHttpClient sicuro, configurato per accettare un certificato personalizzato
    private fun getSafeOkHttpClient(): OkHttpClient {
        if (client == null) {
            try {
                // Carica il certificato del server dalla cartella delle risorse
                val certificateInputStream = context.resources.openRawResource(R.raw.nginxSelfsigned)
                // Crea un KeyStore con il certificato del server
                val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
                keyStore.load(null)
                val certificateFactory = CertificateFactory.getInstance("X.509")
                val certificate = certificateFactory.generateCertificate(certificateInputStream)
                keyStore.setCertificateEntry("server", certificate)

                // Crea un TrustManager che utilizzi il KeyStore con il certificato del server
                val trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
                trustManagerFactory.init(keyStore)

                // Configura un SSLContext con il TrustManager personalizzato
                val sslContext = SSLContext.getInstance("TLS")
                sslContext.init(
                    null,
                    trustManagerFactory.trustManagers,
                    java.security.SecureRandom()
                )

                // Configura l'OkHttpClient per utilizzare l'SSLContext personalizzato
                val builder = OkHttpClient.Builder()
                    .sslSocketFactory(
                        sslContext.socketFactory,
                        trustManagerFactory.trustManagers[0] as X509TrustManager
                    )
                    .hostnameVerifier { _, _ -> true }

                client = builder.build()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }

        return client!!
    }

    // Inizializzazione di Moshi per la deserializzazione dei dati JSON
    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // Inizializzazione di Retrofit per usare il client OkHttp personalizzato e Moshi come convertitore JSON
    private val retrofitBrawler = Retrofit.Builder()
        .client(getSafeOkHttpClient())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    // Creazione del servizio di interfaccia per l'API brawler utilizzando Retrofit
    val brawlerApiService: ApiService by lazy {
        retrofitBrawler.create(ApiService::class.java)
    }
}