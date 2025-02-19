<template>
  <h3>ホーム画面</h3>
  <v-card max-width="500" outlined style="margin: 20px">
    <v-card-subtitle class="text-h6">プロジェクト一覧</v-card-subtitle>
    <v-list subheader>
      <v-list-item
        v-for="project in fetchedProjects?.projects"
        @click="moveToProject(project)"
        outlined
      >
        <v-list-item-title>
          {{ project.projectName }}
        </v-list-item-title>
        <v-divider></v-divider>
      </v-list-item>
    </v-list>
  </v-card>
</template>

<script setup lang="ts">
import axios from "axios";
import { type Project, type ViewType, ViewTypes } from "@/lib/view/ViewType";
import { onMounted, ref } from "vue";

const url: string = import.meta.env.VITE_SERVICE_URL;

type Emits = {
  (event: "change:viewType", value: ViewType): void;
};
const emits = defineEmits<Emits>();

type FetchProjects = {
  projects: Project[];
};

const fetchedProjects = ref<FetchProjects>();

onMounted(async () => {
  fetchedProjects.value = await fetchProject();
});

async function fetchProject() {
  const response = await axios.get(url + "/api/projects");

  if (200 <= response.status && response.status < 300 && response.data) {
    return response.data as FetchProjects;
  }
  alert("プロジェクトの取得に失敗しました。");
}

function moveToProject(project: Project) {
  const viewType: ViewType = {
    viewType: ViewTypes.Project,
    project: project,
  };
  emits("change:viewType", viewType);
}
</script>
