<template>
  <v-app>
    <v-main :style="style">
      <h3>{{ props.errorMessage }}</h3>
    </v-main>
  </v-app>
</template>

<script setup lang="ts">
import { computed } from "vue";

type ThymeleafProps = {
  errorMessage?: string;
};
let props: ThymeleafProps;
try {
  const thymeleafProps = document.getElementById("app")?.dataset?.props; // TODO Thymeleaf からの値の受け渡しがこれで本当にいいのか精査が必要
  if (!thymeleafProps) {
    throw new Error();
  }

  props = JSON.parse(thymeleafProps) as ThymeleafProps; // TODO 型ガードがほしい
} catch (error) {
  alert(
    "情報を取得できませんでした。お手数おかけしますが、時間をおいて再度お試しいただけますと幸いです。",
  );
  window.location.href = "/";
}

const style = computed(() => {
  return {
    width: "450px",
    marginLeft: "auto",
    marginRight: "auto",
    marginTop: "50px",
  };
});
</script>
