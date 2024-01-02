<template>
  <div>
    <div class="d-flex justify-content-center">
      <div class="form-outline me-3">
        <select id="form1" class="btn btn-primary dropdown-toggle form-control" aria-expanded="false"
                v-model="selectedCategory">
          <option class="dropdown-item" v-for="category in categories" :key="category.id" :value="category.id">
            {{ category.name }}
          </option>
        </select>
        <label class="form-label" for="form1">Category</label>
      </div>
      <button :disabled="!selectedCategory" class="btn btn-primary" @click="getRandomValue">Get Random Value</button>
    </div>
    <div>
      <h3 v-if="randomValue">{{ randomValue.name }} - {{ randomValue.description }}</h3>
    </div>
  </div>
</template>

<script>
import apiClient from '../services/api';

export default {
  props: {
    categories: {
      type: Array,
      required: true
    }
  },
  data() {
    return {
      randomValue: null,
      selectedCategory: null
    };
  },
  methods: {
    getRandomValue() {
      apiClient.get(`/api/1/random/value/by/${this.selectedCategory}`)
          .then(response => {
            this.randomValue = response.data;
          })
          .catch(error => {
            console.error('Error fetching random value:', error);
          });
    }
  }
};
</script>
