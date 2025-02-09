import "@mdi/font/css/materialdesignicons.css";
import { createVuetifyApp } from "@/createAppWithVuetify";
import LoginPage from "@/pages/login/LoginPage.vue";

createVuetifyApp(LoginPage).mount("#app");
