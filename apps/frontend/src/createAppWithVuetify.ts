import { createApp } from 'vue';
import { createVuetify } from 'vuetify';
import * as components from 'vuetify/components';
import * as directives from 'vuetify/directives';

const vuetify = createVuetify({
  components,
  directives,
});

export function createVuetifyApp(component: any) {
  const app = createApp(component);
  app.use(vuetify);
  return app;
}
