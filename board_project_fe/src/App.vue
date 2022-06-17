<template>
    <v-app>
        <!-- <div id="app">-->
        <Register></Register>
        <List></List>
        <v-btn depressed="depressed" @click="setDispatchTest">action Test</v-btn>
        {{ test1 }}
        <v-btn depressed="depressed" @click="setCommitTest">mutation Test</v-btn>
        {{ test2 }}
        <nav>
            <router-link to="/">Home</router-link>
            |
            <router-link to="/about">About</router-link>
        </nav>
        <router-view/>
        <!-- </div> -->
    </v-app>
</template>

<script>
    import {mapState, mapActions, mapMutations} from 'vuex';
    import List from './views/BoardList.vue';
    import Register from './views/BoardRegister.vue';

    export default {
        name: 'App',
        components: {
            List,
            Register
        },
        computed: {
            ...mapState(['test1', 'test2'])
        },
        methods: {
            ...mapActions(['TIME']),
            ...mapMutations(['SET_TEST2']),
            setDispatchTest() {
                this.TIME('test1');
            },
            setCommitTest() {
                this.SET_TEST2('test2');
            }
        }
    }
</script>

<style lang="scss">
    #app {
        font-family: 'Kdam Thmor Pro', sans-serif;
        font-family: 'Noto Sans KR', sans-serif;
        font-family: 'Song Myung', serif;
        text-align: center;
    }

    nav {
        padding: 30px;

        a {
            font-weight: bold;
            color: #2c3e50;

            &.router-link-exact-active {
                color: #42b983;
            }
        }
    }
</style>