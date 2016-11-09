/*
 * *
 *   * Copyright 2015 Accela, Inc.
 *   *
 *   * You are hereby granted a non-exclusive, worldwide, royalty-free license to
 *   * use, copy, modify, and distribute this software in source code or binary
 *   * form for use in connection with the web services and APIs provided by
 *   * Accela.
 *   *
 *   * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 *   * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *   * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 *   * DEALINGS IN THE SOFTWARE.
 *   *
 *
 */

package test.eric.com.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by eyang on 10/24/16.
 */
public class HttpRequest {
    static String TAG = "HttpRequest";
    public static String getJSON(String url) {
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.connect();
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    return sb.toString();
            }

        } catch (MalformedURLException ex) {
            Log.e(TAG, ex.toString());
        } catch (IOException ex) {
            Log.e(TAG, ex.toString());
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    Log.e(TAG, ex.toString());
                }
            }
        }
        return null;
    }

    public static Bitmap downloadBitmap(String urlStr) {
        HttpURLConnection urlConnection = null;
        InputStream input = null;
        try {
            URL url = new URL(urlStr);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setConnectTimeout(6 * 1000);
            input = urlConnection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            try {
                if (input != null){
                    input.close();
                }
            }catch (final IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String jsonArray = "{\"photos\":{\"page\":1,\"pages\":3007,\"perpage\":100,\"total\":\"300613\",\"photo\":[{\"id\":\"30543985425\",\"owner\":\"97875832@N03\",\"secret\":\"f1f73d8660\",\"server\":\"5485\",\"farm\":6,\"title\":\"DSC_6376\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30245666820\",\"owner\":\"145306394@N03\",\"secret\":\"12eb5202cb\",\"server\":\"5487\",\"farm\":6,\"title\":\"_DSC0159\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30507812686\",\"owner\":\"135838473@N03\",\"secret\":\"a0af876717\",\"server\":\"5809\",\"farm\":6,\"title\":\"P1018721ww\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30456383291\",\"owner\":\"9183414@N07\",\"secret\":\"e9bd3f48b1\",\"server\":\"5341\",\"farm\":6,\"title\":\"IMG_1126b\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30245573110\",\"owner\":\"143929405@N06\",\"secret\":\"9b5e2a7a16\",\"server\":\"5814\",\"farm\":6,\"title\":\"Emite la Secretar\\u00eda de Desarrollo Urbano y Ecolog\\u00eda  recomendaciones para cuidar a las mascotas del fr\\u00edo.  #gobiernotransversal #gobiernodechihuahua #javiercorral #mexico #chihuahuamx #amaneceparatodos #pets #mascotas #petcare #noticia#noticias #news\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29910564153\",\"owner\":\"145575894@N02\",\"secret\":\"22e991b693\",\"server\":\"5763\",\"farm\":6,\"title\":\"obo-90.jpg\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29912785924\",\"owner\":\"38010018@N08\",\"secret\":\"9dc74822fd\",\"server\":\"5636\",\"farm\":6,\"title\":\"DSCF9776\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30455734721\",\"owner\":\"38010018@N08\",\"secret\":\"d7d70ce8c6\",\"server\":\"5797\",\"farm\":6,\"title\":\"DSCF9780\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30543152195\",\"owner\":\"38010018@N08\",\"secret\":\"01bd0758d6\",\"server\":\"5465\",\"farm\":6,\"title\":\"DSCF9790\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30506977656\",\"owner\":\"38010018@N08\",\"secret\":\"3a23221c97\",\"server\":\"5757\",\"farm\":6,\"title\":\"DSCF9795\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30245296860\",\"owner\":\"124588349@N08\",\"secret\":\"163afb8120\",\"server\":\"5604\",\"farm\":6,\"title\":\"Nine Lives 2016\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29913001624\",\"owner\":\"146067540@N04\",\"secret\":\"b67a7ae5c1\",\"server\":\"5674\",\"farm\":6,\"title\":\"Second Cat-4027\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30455960671\",\"owner\":\"48777250@N06\",\"secret\":\"a388d3d05f\",\"server\":\"5483\",\"farm\":6,\"title\":\"~ Jini ~\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30507418356\",\"owner\":\"51202888@N00\",\"secret\":\"c6f608af05\",\"server\":\"5489\",\"farm\":6,\"title\":\"Megara is being cute.\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30426756112\",\"owner\":\"144382072@N05\",\"secret\":\"c2493c18be\",\"server\":\"5709\",\"farm\":6,\"title\":\"\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30245159190\",\"owner\":\"144382072@N05\",\"secret\":\"18f213112f\",\"server\":\"5664\",\"farm\":6,\"title\":\"\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30455903141\",\"owner\":\"145688356@N03\",\"secret\":\"ace2a47256\",\"server\":\"5815\",\"farm\":6,\"title\":\"14691935_10210696467030633_3254911590345951548_o\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30507378616\",\"owner\":\"9329791@N06\",\"secret\":\"f4c4ed4126\",\"server\":\"5802\",\"farm\":6,\"title\":\"24.10.16 #PAD2016\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29912698664\",\"owner\":\"50915488@N00\",\"secret\":\"6a3184b31b\",\"server\":\"5441\",\"farm\":6,\"title\":\"\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30507215496\",\"owner\":\"132232824@N04\",\"secret\":\"d1cf07a627\",\"server\":\"5506\",\"farm\":6,\"title\":\"IMG_8465\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30543257605\",\"owner\":\"132232824@N04\",\"secret\":\"beeae6dedd\",\"server\":\"5536\",\"farm\":6,\"title\":\"IMG_8460\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30507213196\",\"owner\":\"98721957@N05\",\"secret\":\"8d65c9fb80\",\"server\":\"8677\",\"farm\":9,\"title\":\"Black kitten hidden in sun flowers - from Latvia\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30455659991\",\"owner\":\"98663001@N08\",\"secret\":\"e972d2489b\",\"server\":\"5336\",\"farm\":6,\"title\":\"IMG_0841\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30507150346\",\"owner\":\"70942374@N00\",\"secret\":\"13f386f220\",\"server\":\"8562\",\"farm\":9,\"title\":\"\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29912590744\",\"owner\":\"145128592@N06\",\"secret\":\"93643e0f05\",\"server\":\"8631\",\"farm\":9,\"title\":\"@sg_Pres: #Flipthe49th https:\\/\\/t.co\\/nPLMGJjpa3\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29909777813\",\"owner\":\"98663001@N08\",\"secret\":\"43d79838a5\",\"server\":\"8641\",\"farm\":9,\"title\":\"IMG_0848\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30455311421\",\"owner\":\"98663001@N08\",\"secret\":\"023c4f83af\",\"server\":\"5458\",\"farm\":6,\"title\":\"IMG_0896\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30244608650\",\"owner\":\"98663001@N08\",\"secret\":\"9c6b9d238e\",\"server\":\"5615\",\"farm\":6,\"title\":\"IMG_0897\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29912051834\",\"owner\":\"98663001@N08\",\"secret\":\"e0df59bc57\",\"server\":\"5718\",\"farm\":6,\"title\":\"IMG_0907\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29911925894\",\"owner\":\"98663001@N08\",\"secret\":\"68ff5c4200\",\"server\":\"5561\",\"farm\":6,\"title\":\"IMG_0925\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30425750812\",\"owner\":\"98663001@N08\",\"secret\":\"9ac848fc50\",\"server\":\"5809\",\"farm\":6,\"title\":\"IMG_0926\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30244222880\",\"owner\":\"98663001@N08\",\"secret\":\"6682008a10\",\"server\":\"8661\",\"farm\":9,\"title\":\"IMG_0928\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30454806261\",\"owner\":\"98663001@N08\",\"secret\":\"c764efc825\",\"server\":\"5573\",\"farm\":6,\"title\":\"IMG_0934\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29909077103\",\"owner\":\"98663001@N08\",\"secret\":\"aab653d3db\",\"server\":\"5827\",\"farm\":6,\"title\":\"IMG_0983\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29911608784\",\"owner\":\"98663001@N08\",\"secret\":\"89f5bdea15\",\"server\":\"5613\",\"farm\":6,\"title\":\"IMG_1202\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30454577931\",\"owner\":\"98663001@N08\",\"secret\":\"8409e25bb0\",\"server\":\"5706\",\"farm\":6,\"title\":\"IMG_1338\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30542041815\",\"owner\":\"98663001@N08\",\"secret\":\"08f3059bc0\",\"server\":\"8648\",\"farm\":9,\"title\":\"IMG_1345\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30454500671\",\"owner\":\"98663001@N08\",\"secret\":\"d84a85a9ae\",\"server\":\"5697\",\"farm\":6,\"title\":\"IMG_1348\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29908705983\",\"owner\":\"98663001@N08\",\"secret\":\"2ef3183607\",\"server\":\"5593\",\"farm\":6,\"title\":\"IMG_1358\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29908637863\",\"owner\":\"98663001@N08\",\"secret\":\"8e8372eaf2\",\"server\":\"5529\",\"farm\":6,\"title\":\"IMG_1550\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29911293484\",\"owner\":\"98663001@N08\",\"secret\":\"4a5049b86e\",\"server\":\"5324\",\"farm\":6,\"title\":\"IMG_1991\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30541718075\",\"owner\":\"98663001@N08\",\"secret\":\"8407cf0301\",\"server\":\"5514\",\"farm\":6,\"title\":\"IMG_1996\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29911174724\",\"owner\":\"98663001@N08\",\"secret\":\"708d24dcee\",\"server\":\"5811\",\"farm\":6,\"title\":\"IMG_4661\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30425063412\",\"owner\":\"98663001@N08\",\"secret\":\"8429d43848\",\"server\":\"5760\",\"farm\":6,\"title\":\"IMG_4665\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30425003952\",\"owner\":\"98663001@N08\",\"secret\":\"45ceabb8ce\",\"server\":\"5783\",\"farm\":6,\"title\":\"IMG_5312\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29911025014\",\"owner\":\"98663001@N08\",\"secret\":\"f41e70a533\",\"server\":\"5621\",\"farm\":6,\"title\":\"IMG_5923\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30424844702\",\"owner\":\"98663001@N08\",\"secret\":\"5700ee8af6\",\"server\":\"5673\",\"farm\":6,\"title\":\"IMG_0300\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30424841222\",\"owner\":\"98663001@N08\",\"secret\":\"1769e76521\",\"server\":\"5491\",\"farm\":6,\"title\":\"IMG_1354\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30424780322\",\"owner\":\"98663001@N08\",\"secret\":\"1a460ca352\",\"server\":\"5639\",\"farm\":6,\"title\":\"IMG_2296_Snapseed\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29908144793\",\"owner\":\"98663001@N08\",\"secret\":\"eacfa85c6c\",\"server\":\"5810\",\"farm\":6,\"title\":\"Timian18\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29909886303\",\"owner\":\"96168195@N00\",\"secret\":\"a1de2782dc\",\"server\":\"5762\",\"farm\":6,\"title\":\"Sirikit & Kitty #kitten #cat #catsofinstagram #beautiful #love #pet #photooftheday #likemypet #kitty #feline #yourcatstoday #showcasingpets #petstagram\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30507041446\",\"owner\":\"135559581@N04\",\"secret\":\"12162c34b8\",\"server\":\"5573\",\"farm\":6,\"title\":\"DSC_1842\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30455406011\",\"owner\":\"130030125@N07\",\"secret\":\"d0f34245ae\",\"server\":\"8681\",\"farm\":9,\"title\":\"Ehi, umano. Io non ti temo! #cat #meow #pet #animal #animallovers #instant #instagram #instagramhub #pets #petsagram #puppy #puppylove #nice #love #puppylove #picoftheday #photooftheday #likeforlike\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30455396281\",\"owner\":\"70942374@N00\",\"secret\":\"c6d5ebbbff\",\"server\":\"5467\",\"farm\":6,\"title\":\"\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30506870386\",\"owner\":\"145875266@N07\",\"secret\":\"7acf18594a\",\"server\":\"5326\",\"farm\":6,\"title\":\"\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30426208142\",\"owner\":\"81518092@N00\",\"secret\":\"1cb1c5af2c\",\"server\":\"5590\",\"farm\":6,\"title\":\"\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30506851276\",\"owner\":\"81518092@N00\",\"secret\":\"84bf705474\",\"server\":\"5340\",\"farm\":6,\"title\":\"\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29909703683\",\"owner\":\"81518092@N00\",\"secret\":\"85347cd74d\",\"server\":\"5577\",\"farm\":6,\"title\":\"\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30506851436\",\"owner\":\"81518092@N00\",\"secret\":\"81b979645f\",\"server\":\"5568\",\"farm\":6,\"title\":\"\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30244729660\",\"owner\":\"23360598@N03\",\"secret\":\"bafe9f5c55\",\"server\":\"8652\",\"farm\":9,\"title\":\"DSC01651\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29912336384\",\"owner\":\"100847449@N06\",\"secret\":\"c1ea49c291\",\"server\":\"5532\",\"farm\":6,\"title\":\"Good night #grx #germanrex #rexesofig #rarecatbreed #oneworldcats #excellent_kittens #excellent_cats #germanrexkatze\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30542880555\",\"owner\":\"100847449@N06\",\"secret\":\"00959e5a0a\",\"server\":\"5703\",\"farm\":6,\"title\":\"Lilla fr\\u00f6ken #grx #germanrex #rexesofig #rarecatbreed #oneworldcats #excellent_kittens #excellent_cats #germanrexkatze\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30506798066\",\"owner\":\"63162484@N03\",\"secret\":\"195f98e14c\",\"server\":\"5347\",\"farm\":6,\"title\":\"Bianca yeniden\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30426148322\",\"owner\":\"32713096@N08\",\"secret\":\"a1513fa826\",\"server\":\"5542\",\"farm\":6,\"title\":\"Foofa\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30244655350\",\"owner\":\"32713096@N08\",\"secret\":\"1ff8292a2b\",\"server\":\"5562\",\"farm\":6,\"title\":\"Krimson\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29912189894\",\"owner\":\"99117316@N03\",\"secret\":\"0a210a85da\",\"server\":\"5540\",\"farm\":6,\"title\":\"How to make me smile 301.\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30455218981\",\"owner\":\"131704658@N05\",\"secret\":\"84628166f9\",\"server\":\"5627\",\"farm\":6,\"title\":\"Ipad1\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30455201271\",\"owner\":\"131704658@N05\",\"secret\":\"b40248ff36\",\"server\":\"5762\",\"farm\":6,\"title\":\"Ipad1\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30506612566\",\"owner\":\"89885400@N07\",\"secret\":\"4cc1e7745f\",\"server\":\"5653\",\"farm\":6,\"title\":\"Wise Cats. #cats #kittens #pets #animals #theshining #stanleykubrick #stephenking #horror #horrormovies #halloween #spooky #creepy #catloversofinstagram #catsofinstagram #lol #humor #humorous #funny #funnies #animalplanet #petco #petsmart #aspca #veterina\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29909475693\",\"owner\":\"49844300@N05\",\"secret\":\"ab7620a5c6\",\"server\":\"5470\",\"farm\":6,\"title\":\"Cos you are GOLD! #earworm @beer_n_kittens; )\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30542678275\",\"owner\":\"144916840@N02\",\"secret\":\"150635f464\",\"server\":\"5675\",\"farm\":6,\"title\":\"Brushing Teeth\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29912100224\",\"owner\":\"90273871@N07\",\"secret\":\"843797783c\",\"server\":\"5606\",\"farm\":6,\"title\":\"Zzzz\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29912028524\",\"owner\":\"22428975@N06\",\"secret\":\"bbce5203be\",\"server\":\"5347\",\"farm\":6,\"title\":\"So, we've settled on the candy skeleton shirt, instead.\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30542524305\",\"owner\":\"95100042@N02\",\"secret\":\"4b1242c386\",\"server\":\"5670\",\"farm\":6,\"title\":\"Shiro\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30506390846\",\"owner\":\"95100042@N02\",\"secret\":\"993fd729c8\",\"server\":\"5521\",\"farm\":6,\"title\":\"Speedy & Shiro\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30542503555\",\"owner\":\"95100042@N02\",\"secret\":\"74f8d6e931\",\"server\":\"5484\",\"farm\":6,\"title\":\"Speedy\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29911950984\",\"owner\":\"95100042@N02\",\"secret\":\"fbabd978ee\",\"server\":\"5619\",\"farm\":6,\"title\":\"Sunny\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30506339976\",\"owner\":\"95100042@N02\",\"secret\":\"f87f1b1117\",\"server\":\"8614\",\"farm\":9,\"title\":\"Speedy\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30506334546\",\"owner\":\"95100042@N02\",\"secret\":\"04595d2f0c\",\"server\":\"5774\",\"farm\":6,\"title\":\"Speedy\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30244295580\",\"owner\":\"37625406@N05\",\"secret\":\"2c6c6ba1b5\",\"server\":\"8410\",\"farm\":9,\"title\":\"YA9A4915\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30425771792\",\"owner\":\"95100042@N02\",\"secret\":\"00135e6307\",\"server\":\"5546\",\"farm\":6,\"title\":\"Speedy\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30542446675\",\"owner\":\"95100042@N02\",\"secret\":\"6b7ec506d6\",\"server\":\"8642\",\"farm\":9,\"title\":\"Sora\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30244274200\",\"owner\":\"95100042@N02\",\"secret\":\"7b362f30d9\",\"server\":\"5737\",\"farm\":6,\"title\":\"Sora\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29911894244\",\"owner\":\"144380362@N02\",\"secret\":\"f9064e70b4\",\"server\":\"5328\",\"farm\":6,\"title\":\"Portrait\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30454873101\",\"owner\":\"46461316@N03\",\"secret\":\"2990686fc0\",\"server\":\"5567\",\"farm\":6,\"title\":\"P_20161021_221455_DF-01\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29911875434\",\"owner\":\"147980458@N08\",\"secret\":\"43c6385a56\",\"server\":\"5333\",\"farm\":6,\"title\":\"Merhaba ben bulut! B\\u00fcy\\u00fcd\\u00fcm ben...\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30425720262\",\"owner\":\"147980458@N08\",\"secret\":\"7f1f4d0ed0\",\"server\":\"5694\",\"farm\":6,\"title\":\"Pike'de hamak keyfi...\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30506208286\",\"owner\":\"144482016@N05\",\"secret\":\"3f157874a2\",\"server\":\"5482\",\"farm\":6,\"title\":\"Pippa (left) and Kate (right) are two beautiful girls who were kind enough to pose for our cameras! \\ud83d\\ude0d\\ud83d\\udc31\\u2764\\ufe0f #veterinarian #cute #lakeshoreroadanimalhospital #catsofinstagram #vetclinic\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30425654412\",\"owner\":\"15626374@N00\",\"secret\":\"b21417c2c5\",\"server\":\"5799\",\"farm\":6,\"title\":\"IMG_4568\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29911785694\",\"owner\":\"148543304@N04\",\"secret\":\"c100405b6f\",\"server\":\"8640\",\"farm\":9,\"title\":\"cat eyes\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30244058170\",\"owner\":\"143919671@N07\",\"secret\":\"c03176108b\",\"server\":\"8657\",\"farm\":9,\"title\":\"After 29 years of being around people with cat allergies I was finally able to bring this ball of sweetness home with me via http:\\/\\/ift.tt\\/29KELz0\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30542166005\",\"owner\":\"72696879@N06\",\"secret\":\"217759fd7c\",\"server\":\"5587\",\"farm\":6,\"title\":\"kral-0854\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30454548101\",\"owner\":\"86684296@N06\",\"secret\":\"597e5f972b\",\"server\":\"5808\",\"farm\":6,\"title\":\"Meet Mr. Pickle our newest member of our Wag N Go cat sitting. #wagngo #HTers #HashTags #adorable #animal #animals #cat #catlover #catoftheday #cats #catsagram #catstagram #furry #ilovemycat #instacat #instagood #instagramcats #kitten #kittens #kitty #lov\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29908786463\",\"owner\":\"143448264@N07\",\"secret\":\"281960a638\",\"server\":\"5573\",\"farm\":6,\"title\":\"kitten\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30454408941\",\"owner\":\"91056457@N05\",\"secret\":\"a6f6eb016a\",\"server\":\"5330\",\"farm\":6,\"title\":\"DSCF4424\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29911431784\",\"owner\":\"89188268@N02\",\"secret\":\"fcb5d7f1e7\",\"server\":\"8643\",\"farm\":9,\"title\":\"Mother and baby kitten watercolor\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29908719153\",\"owner\":\"91056457@N05\",\"secret\":\"950fec1665\",\"server\":\"5472\",\"farm\":6,\"title\":\"DSCF4423\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30243678150\",\"owner\":\"91056457@N05\",\"secret\":\"c55c9ffff7\",\"server\":\"5325\",\"farm\":6,\"title\":\"DSCF4414\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30505530586\",\"owner\":\"130725894@N03\",\"secret\":\"e1430c9fde\",\"server\":\"5480\",\"farm\":6,\"title\":\"Sassy Cat\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"30543791575\",\"owner\":\"59537357@N07\",\"secret\":\"1138472baf\",\"server\":\"5676\",\"farm\":6,\"title\":\"Noticed\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0}]},\"stat\":\"ok\"}";



}
