/*
   Copyright (c) 2015, Majenko Technologies
   All rights reserved.

   Redistribution and use in source and binary forms, with or without modification,
   are permitted provided that the following conditions are met:

 * * Redistributions of source code must retain the above copyright notice, this
     list of conditions and the following disclaimer.

 * * Redistributions in binary form must reproduce the above copyright notice, this
     list of conditions and the following disclaimer in the documentation and/or
     other materials provided with the distribution.

 * * Neither the name of Majenko Technologies nor the names of its
     contributors may be used to endorse or promote products derived from
     this software without specific prior written permission.

   THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
   ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
   WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
   DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
   ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
   (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
   LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
   ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
   (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
   SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

/* Create a WiFi access point and provide a web server on it. */

#include <ESP8266WiFi.h>
// #include <WiFiClient.h>
// #include <ESP8266WebServer.h>

#ifndef APSSID
#define APSSID "teste"
#define APPSK "12345678"
#endif

/* Set these to your desired credentials. */
const char *ssid = APSSID;
const char *password = APPSK;
const int GREEN_LED = 16;
const int YELLOW_LED = 5;
const int RED_LED = 4;

WiFiServer server(80);

/* Just a little test message.  Go to http://192.168.4.1 in a web browser
   connected to this access point to see it.
*/

IPAddress local_IP(192, 168, 1, 184);
IPAddress gateway(192, 168, 1, 1);
IPAddress subnet(255, 255, 255, 0);

void setup() {
  delay(1000);
  Serial.begin(9600);
  Serial.println();
  Serial.print("Configuring access point...");
  pinMode(GREEN_LED, OUTPUT);
  digitalWrite(GREEN_LED, LOW);
  pinMode(YELLOW_LED, OUTPUT);
  digitalWrite(YELLOW_LED, LOW);
  pinMode(RED_LED, OUTPUT);
  digitalWrite(RED_LED, LOW);


  if (!WiFi.config(local_IP, gateway, subnet)) {
    Serial.println("STA Failed to configure");
  }

  WiFi.softAP(ssid, password);

  Serial.println();
  Serial.println("Connected to WiFi");
  server.begin();
  IPAddress myIP = WiFi.localIP();
  Serial.print("AP IP address: ");
  Serial.println(myIP);

  Serial.println("HTTP server started");
}

void loop() {
  WiFiClient client = server.available();

  if (!client) {
    return;
  }

  Serial.println("Waiting for new client");

  while (!client.available()) {
    delay(1);

    String request = client.readStringUntil('\r');
    Serial.println(request);
    client.flush();

    int value = LOW;

    if (request.indexOf("/MODE=ON&LED=") != -1) {
      int ledNumber = request.substring(request.indexOf("LED=") + 4).toInt();
      int LED_PIN = getLEDNumber(ledNumber);
      if (LED_PIN == -1) {
        checkLEDIsInvalid(client, LED_PIN);
      }
      else {
        digitalWrite(LED_PIN, HIGH);
      }
    }

    if (request.indexOf("/MODE=OFF&LED=") != -1) {
      int ledNumber = request.substring(request.indexOf("LED=") + 4).toInt();
      Serial.println("Led Number: " + ledNumber);
      int LED_PIN = getLEDNumber(ledNumber);
      if (LED_PIN == -1) {
        checkLEDIsInvalid(client, LED_PIN);
      }
      else {
        digitalWrite(LED_PIN, LOW);
      }
    }

    client.println("HTTP/1.1 200 OK");
    client.println("Content-Type: text/html");
    client.println("");
    client.println("<!DOCTYPE HTML>");
    client.println("<html>");

    client.print("CONTROL LED: ");

    client.println("<br><br>");
    client.println("<a href=\"/LED=ON\"\"><button>ON</button></a>");
    client.println("<a href=\"/LED=OFF\"\"><button>OFF</button></a><br />");
    client.println("</html>");

    delay(1);

    Serial.println("Client disconnected");
    Serial.println("");
  }
}

void checkLEDIsInvalid(WiFiClient client, int LED_PIN) {
  client.println("HTTP/1.1 400 Bad Request");
  client.println("Content-Type: text/plain");
  client.println();
  client.println("Invalid LED number");
}

int getLEDNumber(int ledNumber) {
  int LED_PIN;
  switch (ledNumber) {
    case 1:
      LED_PIN = GREEN_LED;
      break;
    case 2:
      LED_PIN = YELLOW_LED;
      break;
    case 3:
      LED_PIN = RED_LED;
      break;
    default:
      LED_PIN = -1;
      break;
  }

  return LED_PIN;
}