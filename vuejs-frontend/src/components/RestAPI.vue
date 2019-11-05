<template>
  <div>
    <v-container fluid text-center>
      <v-row>
        <v-col cols="12">
          <h1 class="text-center mb-3">Play with Rest API</h1>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <v-card tile >
            <v-card-text class="text-justify">
              <p class="mx-5">MISC VALUES
                <ul>
                  <li>Target URL: <code>{{ targetUrl }}</code></li>
                  <li>Application Base URL: <code>{{ baseUrl }}</code></li>
                </ul>
              </p>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="12">
          <v-btn color="success" @click="callRestService()">CALL Spring Boot REST backend service from VueJS</v-btn>
        </v-col>
      </v-row>
      <v-row>
        <v-col cols="8">
          <v-card tile >
            <v-list flat>
              <div class="overline mb-4 blue--text">MESSAGES</div>
              <v-list-item-group>
                <v-list-item v-for="(item, index) in responses" :key="index">
                  <v-list-item-content class="text-justify">
                    <span>Call #{{index}} : {{item === null ? 'no message' : 'id: '+item.id+', "'+item.content+'"'}}</span>
                  </v-list-item-content>
                </v-list-item>
              </v-list-item-group>
            </v-list>
          </v-card>
        </v-col>
        <v-col cols="4">
          <v-card tile>
            <v-list flat>
              <div class="overline mb-4 red--text">ERRORS</div>
              <v-list-item-group>
                <v-list-item v-for="(item, index) in errors" :key="index">
                  <v-list-item-content class="text-justify red--text">
                    <span>Call #{{index}} : {{item === null ? 'no error' : '"'+item+'"'}}</span>
                  </v-list-item-content>
                </v-list-item>
              </v-list-item-group>
            </v-list>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
// import {AXIOS} from "@/services/http.service";
import axios from "axios";

export default {
  data() {
    return {
      targetUrl: "",
      baseUrl: "",
      responses: [],
      errors: []
    };
  },
  mounted: function() {
    this.targetUrl = process.env.VUE_APP_REMOTE_SERVER + `/api/public/hello`;
    this.baseUrl = process.env.BASE_URL;
  },
  methods: {
    callRestService() {
      axios
        .get(this.targetUrl)
        .then(response => {
          // JSON responses are automatically parsed.
          this.responses.push(response.data);
          this.errors.push(null);
        })
        .catch(e => {
          this.responses.push(null);
          this.errors.push(e);
        });
    }
  }
};
</script>

<style>
</style>