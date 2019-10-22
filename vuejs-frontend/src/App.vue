<template>
  <v-app>
    <v-app-bar app>
      <v-toolbar-title class="headline text-uppercase">
        <span>Vuetify/SpringBoot</span>
        <span class="font-weight-light">Demo</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn
        text
        href="https://github.com/vuetifyjs/vuetify/releases/latest"
        target="_blank"
      >
        <span class="mr-2">Latest Vuetify Release</span>
      </v-btn>
      
    </v-app-bar>

    <v-navigation-drawer
      app
      absolute
      permanent
      left
    >
      <template v-slot:prepend>
        <v-list-item two-line>
          <v-list-item-avatar>
            <img :src="require('./assets/logo.svg')">
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title>Vuetify & Spring</v-list-item-title>
            <v-list-item-subtitle>Material Design</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </template>

      <v-divider></v-divider>

      <v-list dense>
        <v-list-item
          v-for="item in items"
          :key="item.title"
        >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title><router-link :to="item.link">{{ item.title }}</router-link></v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-content>
      <router-view/>
    </v-content>
  </v-app>
</template>

<script>
export default {
  name: 'App',
  data: () => ({
    items: [
          { title: 'Home', icon: 'mdi-home-city', link: '/' },
          { title: 'REST API', icon: 'mdi-account-network', link: '/rest' },
          { title: 'About', icon: 'mdi-comment-question-outline', link: '/about' }
        ],
  }),
};
</script>
