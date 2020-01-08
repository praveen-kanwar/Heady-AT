package com.heady.test.data.webservices.provider.okhttpclient

import android.content.Context
import com.heady.test.data.BuildConfig
import com.heady.test.data.R
import com.heady.test.data.webservices.provider.cache.CacheProvider
import com.heady.test.data.webservices.provider.httplogginginterceptor.HttpLoggingInterceptorProvider
import com.heady.test.data.webservices.provider.interceptor.InterceptorProvider
import com.tejora.utils.Utils
import okhttp3.OkHttpClient
import java.io.IOException
import java.io.InputStream
import java.security.KeyManagementException
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.cert.CertificateException
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

/**
 * OkHttpClient Provider For Retrofit Aiding API Calls
 *
 * Created by Praveen on 01-07-2019.
 */
class OkHttpClientProvider
@Inject
constructor(
    private val context: Context,
    private val httpLoggingInterceptor: HttpLoggingInterceptorProvider,
    private val interceptorProvider: InterceptorProvider,
    private val cacheProvider: CacheProvider,
    private val utils: Utils
) {

    // HttpClient
    val okHttpClient: OkHttpClient get() = setupOkHttpClient()

    /*
     * Setting-up Http Client
     */
    private fun setupOkHttpClient(): OkHttpClient {
        utils.showLog(TAG, "setupOkHttpClient()")
        val okHttpClientBuilder = OkHttpClient.Builder() // Creating OkHttpClientBuilder
            .addInterceptor(interceptorProvider.offlineCacheInterceptor) // Adding Interceptor For Making GET API Available In Offline.
            .addNetworkInterceptor(interceptorProvider.interceptor) // Adding Interceptor For Making A
            .cache(cacheProvider.cache)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
        @Suppress("ConstantConditionIf")
        if (BuildConfig.DEBUG) {
            utils.showLog(TAG, "Enabling Logger If Debug Build")
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor.instance) // Adding Interceptor For Logs Of API Call
        }
        // If Base URL Contain SSL Certificate
        if (BuildConfig.BASE_URL.contains("https://") && !BuildConfig.DEBUG) {
            utils.showLog(TAG, "Pinning Application With SSL")
            // For SSL Pinning
            val trustedCertificate = getTrustedCertificate() // Get Trusted Certificate As Keystore
            val trustManagerFactory =
                getTrustManagerFactory(trustedCertificate) // Get TrustManagerFactory For SSL Context & TrustManager
            val sslContext =
                getSSLContext(trustManagerFactory) // Get SSL Context For SSLSocketFactory
            val trustManager = getTrustManager(trustManagerFactory) // Get Trust Manager
            okHttpClientBuilder.sslSocketFactory(
                sslContext.socketFactory,
                trustManager
            ) // Pinning SSL Certificate
        }
        return okHttpClientBuilder.build()
    }

    /*
     * Get Trusted Certificate As Keystore
     */
    private fun getTrustedCertificate(): KeyStore {
        var trustedKeyStore: KeyStore? = null
        var inputStream: InputStream? = null
        try {
            trustedKeyStore = KeyStore.getInstance(CERTIFICATE_TYPE)
            inputStream = context.resources.openRawResource(R.raw.heady)
            trustedKeyStore!!.load(inputStream, BuildConfig.TRUST_STORE_PASSWORD.toCharArray())
        } catch (e: KeyStoreException) {
            utils.showLog(TAG, "Exception at getTrustedCertificate() ${e.message}")
        } catch (e: CertificateException) {
            utils.showLog(TAG, "Exception at getTrustedCertificate() ${e.message}")
        } catch (e: NoSuchAlgorithmException) {
            utils.showLog(TAG, "Exception at getTrustedCertificate() ${e.message}")
        } catch (e: IOException) {
            utils.showLog(TAG, "Exception at getTrustedCertificate() ${e.message}")
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (e: IOException) {
                    utils.showLog(TAG, "Exception at getTrustedCertificate() ${e.message}")
                }

            }
        }
        utils.showLog(TAG, "getTrustedCertificate() : $trustedKeyStore")
        return trustedKeyStore!!
    }

    /*
     * Get TrustManagerFactory For Initialization Of SSL Context & TrustManager
     */
    private fun getTrustManagerFactory(trustedCertificate: KeyStore): TrustManagerFactory {
        var trustManagerFactory: TrustManagerFactory? = null
        try {
            trustManagerFactory =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            trustManagerFactory!!.init(trustedCertificate)
        } catch (e: NoSuchAlgorithmException) {
            utils.showLog(
                TAG,
                "Exception at getTrustManagerFactory($trustedCertificate) : ${e.message}"
            )
        } catch (e: KeyStoreException) {
            utils.showLog(
                TAG,
                "Exception at getTrustManagerFactory($trustedCertificate) : ${e.message}"
            )
        }
        return trustManagerFactory!!
    }

    /*
     * Get SSL Context Required For Getting SSLSocketFactory Instance Required For SSL Pinning
     */
    private fun getSSLContext(trustManagerFactory: TrustManagerFactory): SSLContext {
        var sslContext: SSLContext? = null
        try {
            sslContext = SSLContext.getInstance(DEFAULT_TLS_VERSION)
            sslContext!!.init(null, trustManagerFactory.trustManagers, null)
        } catch (e: NoSuchAlgorithmException) {
            utils.showLog(TAG, "Exception at getSSLContext($trustManagerFactory) : ${e.message}")
        } catch (e: KeyManagementException) {
            utils.showLog(TAG, "Exception at getSSLContext($trustManagerFactory) : ${e.message}")
        }
        return sslContext!!
    }

    /*
     * Get TrustManager Required For SSL Pinning
     */
    private fun getTrustManager(trustManagerFactory: TrustManagerFactory): X509TrustManager {
        val trustManagers = trustManagerFactory.trustManagers
        if (trustManagers == null
            || trustManagers.size != 1
            || trustManagers[0] !is X509TrustManager
        ) {
            val e = IllegalStateException("Wrong trust manager: " + Arrays.toString(trustManagers))
            utils.showLog(TAG, "Error Occurred While Providing X509 TrustManager ${e.message}")
        }
        return trustManagers[0] as X509TrustManager
    }

    companion object {
        private const val TAG = "OkHttpClientProvider"
        private const val CERTIFICATE_TYPE = "BKS"
        private const val DEFAULT_TLS_VERSION = "TLSv1.2"
    }
}