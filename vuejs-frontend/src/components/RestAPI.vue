<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <h1 class="display-2 font-weight-bold mb-3">Play with Rest API</h1>
        <v-btn
          color="success"
          @click="callRestService()"
        >CALL Spring Boot REST backend service from VueJS</v-btn>
      </v-flex>
      <v-flex text-left xs12>
        <v-card class="mx-auto" max-width="900" tile>
          <v-list flat>
            <v-subheader>MISC VALUES</v-subheader>
            <v-list-item-group color="primary">
              <v-list-item>
                <v-list-item-content>
                  Target URL: "{{ messageTarget }}"
                </v-list-item-content>
              </v-list-item>
              <v-list-item>
                <v-list-item-content>
                  Application Base URL: "{{ messageBaseUrl }}"
                </v-list-item-content>
              </v-list-item>
            </v-list-item-group>
          </v-list>
          <v-list flat>
            <v-subheader>RESPONSES</v-subheader>
            <v-list-item-group color="primary">
              <v-list-item v-for="(item, index) in responses" :key="index">
                <v-list-item-content>
                  <span>#{{index}} : {{item === null ? 'no message' : item}}</span>
                </v-list-item-content>
              </v-list-item>
            </v-list-item-group>
          </v-list>
          <v-list flat>
            <v-subheader>ERRORS</v-subheader>
            <v-list-item-group>
              <v-list-item v-for="(item, index) in errors" :key="index">
                <v-list-item-content>
                  <span>#{{index}} : {{item === null ? 'no error' : item}}</span>
                </v-list-item-content>
              </v-list-item>
            </v-list-item-group>
          </v-list>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
// import {AXIOS} from "@/services/http.service";
import axios from "axios";

export default {
  data() {
    return {
      messageTarget: "",
      messageBaseUrl: "",
      responses: [],
      errors: []
    };
  },
  mounted: function() {
    this.messageTarget = process.env.VUE_APP_REMOTE_SERVER;
    this.messageBaseUrl = process.env.BASE_URL;
  },
  methods: {
    callRestService() {
      axios
        .get(process.env.VUE_APP_REMOTE_SERVER + `/api/hello`)
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