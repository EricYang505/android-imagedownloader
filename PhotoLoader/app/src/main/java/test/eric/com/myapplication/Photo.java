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

/**
 * Created by eyang on 10/24/16.
 */
public class Photo {
    private String id;
    private String secret;
    private String farm;
    private String server;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFarm() {
        return farm;
    }

    public String getServer() {
        return server;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public void setServer(String server) {
        this.server = server;
    }
}
