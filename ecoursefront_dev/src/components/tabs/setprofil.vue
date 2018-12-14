<template>
    <div>
        <a-tabs @change="callback" defaultActiveKey="1">
            <a-tab-pane tab="基础设置" key="1">
                <basicset/>
            </a-tab-pane>
            <a-tab-pane tab="安全设置" key="2">
                <securityset/>
            </a-tab-pane>
        </a-tabs>
    </div>
</template>

<script>
import basicset from '@/components/forms/basicset.vue'
import securityset from '@/components/forms/securityset.vue'

export default {
    name:"setprofil",
    data:function(){
        return {
        }
    },
    methods:{
    },
    components:{
        basicset,
        securityset,
    }
}
</script>