/**
 * Copyright 2021 Yahoo Holdings Inc.
 * Licensed under the terms of the MIT license. See accompanying LICENSE.md file for terms.
 */
import BaseJsonAdapter from 'navi-core/serializers/base-json-serializer';

export default class Application extends BaseJsonAdapter {}

// DO NOT DELETE: this is how TypeScript knows how to look up your serializers.
declare module 'ember-data/types/registries/serializer' {
  export default interface SerializerRegistry {
    application: Application;
  }
}
